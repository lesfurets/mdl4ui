package org.mdl4ui.base.apt;

import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.SimpleAnnotationValueVisitor6;

final class OnElementVisitor extends SimpleAnnotationValueVisitor6<Void, OnElementVisitorContext> {

    @Override
    public Void visitEnumConstant(VariableElement c, OnElementVisitorContext p) {
        p.getElementIds().add(c);
        return null;
    }

    @Override
    public Void visitArray(List<? extends AnnotationValue> vals, OnElementVisitorContext p) {
        for (AnnotationValue annotationValue : vals) {
            visit(annotationValue, p);
        }
        return null;
    }

    @Override
    public Void visitAnnotation(AnnotationMirror a, OnElementVisitorContext p) {
        for (AnnotationValue annotationValue : a.getElementValues().values()) {
            visit(annotationValue, p);
        }
        return null;
    }
}
