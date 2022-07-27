package at.ac.tuwien.big.cps.annotationprocessor.processor;

import com.google.auto.service.AutoService;
import at.ac.tuwien.big.cps.annotationprocessor.annotation.Id;
import at.ac.tuwien.big.cps.annotationprocessor.util.TypeMirrorUtils;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class IdProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager msger = processingEnv.getMessager();
        List<Element> idAnnotatedClasses = new LinkedList<>();

        annotations.stream().forEach(a -> {
            roundEnv.getElementsAnnotatedWith(a).stream().forEach(e -> {
                AnnotationMirror annotationMirror = e.getAnnotationMirrors()
                        .stream()
                        .filter(am -> Id.class.getName().equals(am.getAnnotationType().toString()))
                        .findFirst().get();

                // Is only allowed once per class
                if (idAnnotatedClasses.contains(e.getEnclosingElement())) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "@" + Id.class.getName() + " can only be annotated to one field in a class",
                            e,
                            annotationMirror
                    );
                } else {
                    idAnnotatedClasses.add(e.getEnclosingElement());
                }

                // Must be a String field
                if (!TypeMirrorUtils.isApplicable(e.asType(), List.of(String.class))) {
                    msger.printMessage(
                            Diagnostic.Kind.ERROR,
                            "@" + Id.class.getName() + " annotated fields need to be of type String",
                            e,
                            annotationMirror
                    );
                }

            });
        });
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Id.class.getName());
    }
}
