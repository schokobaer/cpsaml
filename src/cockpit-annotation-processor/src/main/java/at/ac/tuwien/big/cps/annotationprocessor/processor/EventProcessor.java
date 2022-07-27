package at.ac.tuwien.big.cps.annotationprocessor.processor;

import at.ac.tuwien.big.cps.annotationprocessor.annotation.Event;
import at.ac.tuwien.big.cps.annotationprocessor.util.TypeMirrorUtils;
import com.google.auto.service.AutoService;

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
public class EventProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager msger = processingEnv.getMessager();
        Map<TypeMirror, List<String>> eventRuleNames = new HashMap<>();

        annotations.stream().forEach(a -> {
            roundEnv.getElementsAnnotatedWith(a).stream().forEach(e -> {
                AnnotationMirror annotationMirror = e.getAnnotationMirrors()
                        .stream()
                        .filter(am -> Event.class.getName().equals(am.getAnnotationType().toString()))
                        .findFirst().get();


                // return type is boolean
                ExecutableElement method = (ExecutableElement) e;
                if (!TypeMirrorUtils.isApplicable(method.getReturnType(), TypeMirrorUtils.BOOLS)) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "A @EventRule annotated method must have a boolean return type",
                            e,
                            annotationMirror
                    );
                }

                // exactly two arguments
                if (method.getParameters().size() != 2 ||
                        !method.getParameters().get(0).asType().equals(method.getParameters().get(1).asType())) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "A @EventRule annotated method must have exactly two input parameters of the same type",
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
                            "The parameter type of a @EventRule annotated method must have an @Id annotated field",
                            e,
                            annotationMirror
                    );
                }

                // name is unique
                Event critical = e.getAnnotation(Event.class);
                if (critical == null) return;
                String name = critical.value();
                if ("".equals(name)) {
                    name = e.getSimpleName().toString();
                }
                if (!eventRuleNames.containsKey(inputTypeMirror)) {
                    eventRuleNames.put(inputTypeMirror, new LinkedList<>());
                }
                if (eventRuleNames.get(inputTypeMirror).contains(name)) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "A @EventRule annotated method needs to have a unique name",
                            e,
                            annotationMirror
                    );
                } else {
                    eventRuleNames.get(inputTypeMirror).add(name);
                }
            });
        });
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Event.class.getName());
    }
}
