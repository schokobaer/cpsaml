package at.ac.tuwien.big.cps.annotationprocessor.processor;

import com.google.auto.service.AutoService;
import at.ac.tuwien.big.cps.annotationprocessor.annotation.Severity;
import at.ac.tuwien.big.cps.annotationprocessor.model.SeverityLevel;
import at.ac.tuwien.big.cps.annotationprocessor.util.TypeMirrorUtils;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class SeverityProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager msger = processingEnv.getMessager();
        List<String> severityNames = new LinkedList<>();

        annotations.stream().forEach(a -> {
            roundEnv.getElementsAnnotatedWith(a).stream().forEach(e -> {
                AnnotationMirror annotationMirror = e.getAnnotationMirrors()
                        .stream()
                        .filter(am -> Severity.class.getName().equals(am.getAnnotationType().toString()))
                        .findFirst().get();

                // Name is available
                Severity severity = e.getAnnotation(Severity.class);
                if (severity == null) return;
                String name = severity.value();
                if ("".equals(name)) {
                    name = e.getSimpleName().toString();
                }
                if (severityNames.contains(name)) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "A @Severity annotated method needs to have a unique name",
                            e,
                            annotationMirror
                    );
                } else {
                    severityNames.add(name);
                }

                // return type is SeverityLevel
                ExecutableElement method = (ExecutableElement) e;
                if (!TypeMirrorUtils.isApplicable(method.getReturnType(), List.of(SeverityLevel.class))) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "A @Severity annotated method needs to return a " + SeverityLevel.class.getName(),
                            e,
                            annotationMirror
                    );
                }

                // only one or two arguments
                if (method.getParameters().size() > 2 || method.getParameters().isEmpty()) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "A @Severity annotated method must have between one and two input parameters",
                            e,
                            annotationMirror
                    );
                }

                if (method.getParameters().size() == 1) {
                    if (!TypeMirrorUtils.isPrimitiveOrListOfPrimitives(method.getParameters().get(0).asType())) {
                        msger.printMessage(
                                Diagnostic.Kind.ERROR,
                                "A @Severity annotated method must have an input parameter with a primitive or list of primitives type",
                                e,
                                annotationMirror
                        );
                    }
                } else if (method.getParameters().size() == 2) {
                    TypeMirror param1 = method.getParameters().get(0).asType();
                    TypeMirror param2 = method.getParameters().get(1).asType();
                    if (!(TypeMirrorUtils.isPrimitiveOrListOfPrimitives(param1) && TypeMirrorUtils.typeHasIdAnnotatedField(param2))
                        && !(TypeMirrorUtils.isPrimitiveOrListOfPrimitives(param2) && TypeMirrorUtils.typeHasIdAnnotatedField(param1))) {
                        msger.printMessage(
                                Diagnostic.Kind.ERROR,
                                "If a @Severity annotated method has two input parameters one has to have a primitive or list of primitives type while the" +
                                        " other has a type with an @Id annotated field",
                                e,
                                annotationMirror
                        );
                    }
                }

            });
        });
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Severity.class.getName());
    }
}
