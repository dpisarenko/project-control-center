/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.api.dependencieseditingdialogcontroller;

import ru.altruix.commons.api.conventions.SingleActivityModule;
import ru.altruix.commons.api.di.ModuleWithInjectableDependencies;
import com.vaadin.ui.Window;
import at.silverstrike.pcc.api.model.SchedulingObject;

/**
 * @author DP118M
 * 
 */
public interface DependenciesEditingDialogController extends
        SingleActivityModule, ModuleWithInjectableDependencies {
    /**
     * Задаёт расчётный объект, зависимости которого отображаются в окне при
     * старте.
     */
    void setSchedulingObject(final SchedulingObject aObject);

    void setParentWindow(final Window aWindow);    
}
