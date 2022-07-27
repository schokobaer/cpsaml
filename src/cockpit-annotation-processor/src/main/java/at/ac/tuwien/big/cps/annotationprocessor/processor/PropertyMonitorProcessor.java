package at.ac.tuwien.big.cps.annotationprocessor.processor;

import at.ac.tuwien.big.cps.annotationprocessor.annotation.PropertyMonitor;
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
public class PropertyMonitorProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager msger = processingEnv.getMessager();
        Map<TypeMirror, List<String>> propertyNames = new HashMap<>();

        annotations.stream().forEach(a -> {
            roundEnv.getElementsAnnotatedWith(a).stream().forEach(e -> {
                AnnotationMirror annotationMirror = e.getAnnotationMirrors()
                        .stream()
                        .filter(am -> PropertyMonitor.class.getName().equals(am.getAnnotationType().toString()))
                        .findFirst().get();

                // return type is primitive or list of primitives
                ExecutableElement method = (ExecutableElement) e;
                if (!TypeMirrorUtils.isPrimitiveOrListOfPrimitives(method.getReturnType())) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "A @PropertyObjective annotated method must have a primitive, String or List of primitives return type",
                            e,
                            annotationMirror
                    );
                }

                // only one argument
                if (method.getParameters().size() != 1) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "A @PropertyObjective annotated method must have exactly one input parameter",
                            e,
                            annotationMirror
                    );
                }

                // InputType is a class with a @Id annotated field
                TypeMirror inputTypeMirror = method.getParameters().get(0).asType();
                if (!TypeMirrorUtils.typeHasIdAnnotatedField(inputTypeMirror)) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "The parameter type of a @PropertyObjective annotated method must have an @Id annotated field",
                            e,
                            annotationMirror
                    );
                }

                // name is unique
                PropertyMonitor prop = e.getAnnotation(PropertyMonitor.class);
                if (prop == null) return;
                String name = prop.value();
                if ("".equals(name)) {
                    name = e.getSimpleName().toString();
                }
                if (!propertyNames.containsKey(inputTypeMirror)) {
                    propertyNames.put(inputTypeMirror, new LinkedList<>());
                }
                if (propertyNames.get(inputTypeMirror).contains(name)) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "A @PropertyObjective annotated method needs to have a unique name",
                            e,
                            annotationMirror
                    );
                } else {
                    propertyNames.get(inputTypeMirror).add(name);
                }
            });
        });
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(PropertyMonitor.class.getName());
    }
}
