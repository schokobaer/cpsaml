package at.ac.tuwien.big.cps.annotationprocessor.processor;

import com.google.auto.service.AutoService;
import at.ac.tuwien.big.cps.annotationprocessor.annotation.CriticalMonitor;
import at.ac.tuwien.big.cps.annotationprocessor.util.TypeMirrorUtils;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.util.*;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class CriticalMonitorProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager msger = processingEnv.getMessager();
        Map<TypeMirror, List<String>> criticalNames = new HashMap<>();

        annotations.stream().forEach(a -> {
            roundEnv.getElementsAnnotatedWith(a).stream().forEach(e -> {
                AnnotationMirror annotationMirror = e.getAnnotationMirrors()
                        .stream()
                        .filter(am -> CriticalMonitor.class.getName().equals(am.getAnnotationType().toString()))
                        .findFirst().get();


                // return type is boolean
                ExecutableElement method = (ExecutableElement) e;
                if (!TypeMirrorUtils.isApplicable(method.getReturnType(), TypeMirrorUtils.BOOLS)) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "A @CriticalObjective annotated method must have a boolean return type",
                            e,
                            annotationMirror
                    );
                }

                // only one argument
                if (method.getParameters().size() != 1) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "A @CriticalObjective annotated method must have exactly one input parameter",
                            e,
                            annotationMirror
                    );
                    return;
                }

                // InputType is a class with a @Id annotated field
                TypeMirror inputTypeMirror = method.getParameters().get(0).asType();
                if (!TypeMirrorUtils.typeHasIdAnnotatedField(inputTypeMirror)) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "The parameter type of a @CriticalObjective annotated method must have an @Id annotated field",
                            e,
                            annotationMirror
                    );
                }

                // name is unique
                CriticalMonitor critical = e.getAnnotation(CriticalMonitor.class);
                if (critical == null) return;
                String name = critical.value();
                if ("".equals(name)) {
                    name = e.getSimpleName().toString();
                }
                if (!criticalNames.containsKey(inputTypeMirror)) {
                    criticalNames.put(inputTypeMirror, new LinkedList<>());
                }
                if (criticalNames.get(inputTypeMirror).contains(name)) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "A @CriticalObjective annotated method needs to have a unique name",
                            e,
                            annotationMirror
                    );
                } else {
                    criticalNames.get(inputTypeMirror).add(name);
                }
            });
        });
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(CriticalMonitor.class.getName());
    }
}
