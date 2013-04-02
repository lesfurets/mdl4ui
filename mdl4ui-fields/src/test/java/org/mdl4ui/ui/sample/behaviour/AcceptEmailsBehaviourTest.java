/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package org.mdl4ui.ui.sample.behaviour;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mdl4ui.ui.sample.EFieldSample.EMAILS_PREFERENCES;
import static org.mdl4ui.ui.sample.EFieldSample.EMAIL_ACCEPTED;
import static org.mdl4ui.ui.sample.EFieldSample.MAX_WEEKLY_EMAILS;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.mdl4ui.base.model.FieldID;
import org.mdl4ui.fields.model.FieldBehaviour;
import org.mdl4ui.fields.sample.behaviour.MockFieldBehaviourFactory;
import org.mdl4ui.fields.sample.context.SampleContext;
import org.mdl4ui.ui.sample.FieldDependencySampleFactory;

public class AcceptEmailsBehaviourTest {

    @Test
    public void dependencies() {
        Collection<FieldID> dependencies = Arrays.asList(new FieldDependencySampleFactory().get(EMAIL_ACCEPTED));

        assertEquals(2, dependencies.size());
        assertTrue(dependencies.contains(EMAILS_PREFERENCES));
        assertTrue(dependencies.contains(MAX_WEEKLY_EMAILS));
    }

    @Test
    public void dependenciesVisibility() {
        SampleContext context = new SampleContext();

        for (FieldID dependency : new FieldDependencySampleFactory().get(EMAIL_ACCEPTED)) {
            FieldBehaviour behaviour = new MockFieldBehaviourFactory().get(dependency);

            context.getUserAccount().setAcceptEmail(false);
            assertFalse(behaviour.isVisible(dependency, context, null));

            context.getUserAccount().setAcceptEmail(true);
            assertTrue(behaviour.isVisible(dependency, context, null));
        }
    }
}
