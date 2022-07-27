package at.ac.tuwien.big.cps.annotationprocessor.processor;

import at.ac.tuwien.big.cps.annotationprocessor.annotation.Bounds;
import at.ac.tuwien.big.cps.annotationprocessor.util.TypeMirrorUtils;
import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(javax.annotation.processing.Processor.class)
public class BoundsProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager msger = processingEnv.getMessager();
        annotations.stream().forEach(a -> {
            roundEnv.getElementsAnnotatedWith(a).stream().forEach(e -> {
                AnnotationMirror annotationMirror = e.getAnnotationMirrors()
                        .stream()
                        .filter(am -> Bounds.class.getName().equals(am.getAnnotationType().toString()))
                        .findFirst().get();

                // min < max
                Bounds bounds = e.getAnnotation(Bounds.class);
                if (bounds == null) return;
                if (bounds.min() >= bounds.max()) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "min must be smaller than max in @Bounds",
                            e,
                            annotationMirror,
                            annotationMirror.getElementValues().values().stream().collect(Collectors.toList()).get(0)
                    );
                }


                if (e instanceof ExecutableElement) {
                    // method
                    ExecutableElement method = (ExecutableElement) e;

                    // return type is number
                    if (!TypeMirrorUtils.isApplicable(method.getReturnType(), TypeMirrorUtils.NUMBERS)) {
                        msger.printMessage(
                                Diagnostic.Kind.ERROR,
                                "A @Bounds annotated method needs to return a number type, like int, long or double",
                                e,
                                annotationMirror
                        );
                    }
/*
                    // only one argument
                    if (method.getParameters().size() != 1) {
                        msger.printMessage(
                                Diagnostic.Kind.ERROR,
                                "A @Bounds annotated method must have exactly one input parameter",
                                e,
                                annotationMirror
                        );
                    }

                    // PropertyObjective is available
                    if (e.getAnnotation(PropertyObjective.class) == null) {
                        msger.printMessage(
                                Diagnostic.Kind.ERROR,
                                "A @Bounds annotated method must also be annotated with a @" + PropertyObjective.class.getName() + " annotation",
                                e,
                                annotationMirror
                        );
                    }*/
                } else if (e instanceof VariableElement) {
                    VariableElement field = (VariableElement) e;
                    if (!TypeMirrorUtils.isApplicable(field.asType(), TypeMirrorUtils.NUMBERS)) {
                        msger.printMessage(
                                Diagnostic.Kind.ERROR,
                                "A @Bounds annotated field needs to have a number type, like int, long or double",
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
        return Set.of(Bounds.class.getName());
    }
}
