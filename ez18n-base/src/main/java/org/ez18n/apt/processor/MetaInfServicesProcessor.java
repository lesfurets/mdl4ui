package org.ez18n.apt.processor;

import static javax.lang.model.SourceVersion.RELEASE_6;
import static javax.lang.model.element.ElementKind.INTERFACE;
import static javax.tools.Diagnostic.Kind.ERROR;
import static javax.tools.Diagnostic.Kind.NOTE;
import static javax.tools.StandardLocation.SOURCE_OUTPUT;

import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;

import org.ez18n.MessageBundle;

@SupportedAnnotationTypes("org.ez18n.MessageBundle")
@SupportedSourceVersion(RELEASE_6)
public class MetaInfServicesProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if (roundEnv.processingOver())
			return true;
		for (Element element : roundEnv.getElementsAnnotatedWith(MessageBundle.class)) {
			if (element.getKind() != INTERFACE)
				continue;
			try {
				write((TypeElement) element);
			} catch (IOException e) {
				processingEnv.getMessager().printMessage(ERROR, e.getMessage());
			}
		}
		return false;
	}

	private final void write(TypeElement elt) throws IOException {
		final MessageFormat MSG = new MessageFormat("written META-INF/services/{0}");
		Writer writer = null;
		try {
			final FileObject file = processingEnv.getFiler().createResource(SOURCE_OUTPUT, "",
					"META-INF/services/" + elt.getQualifiedName().toString());
			writer = file.openWriter();
			writer.write(DesktopBundleProcessor.getDesktopBundleClassName(elt, true));
			writer.write('\n');
			writer.write(MobileBundleProcessor.getMobileMessagesClassName(elt, true));
			writer.write('\n');
			writer.close();
			processingEnv.getMessager().printMessage(NOTE, MSG.format(new Object[] { file.getName() }));
		} finally {
			if (writer != null)
				writer.close();
		}
	}
}
