/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/
package at.silverstrike.pcc.api.conventions;

import java.util.HashMap;
import java.util.Map;

public final class MessageCodePrefixRegistry {
    private static final String PREFIX_MESSAGE_NUMBER_SEPARATOR = ".";

    public enum Module {
        conventions,
        dailyplanpanel,
        debugids,
        editingprocesspanel,
        embeddedfilereading,
        estimatedcompletiontimespanel,
        export2tj3,
        injectorfactory,
        jruby,
        mainprocesseditingpanel,
        mainwindow,
        model,
        persistence,
        processpanel,
        projectscheduler,
        schedulingpanel,
        tj3bookingsparser,
        tj3deadlinesparser,
        workerpanel,
        version,
        culture2lang,
        entrywindow,
        parameterdatareader
    }

    private Map<Module, String> prefixesByModules;

    private static MessageCodePrefixRegistry instance;

    public static MessageCodePrefixRegistry getInstance() {
        if (instance == null) {
            instance = new MessageCodePrefixRegistry();
        }
        return instance;
    }
    
    public static String getMessageNumberSeparator()
    {
    	return PREFIX_MESSAGE_NUMBER_SEPARATOR;
    }

    public String getPrefix(final Module aModule) {
        return this.prefixesByModules.get(aModule)
                + PREFIX_MESSAGE_NUMBER_SEPARATOR;
    }

    private MessageCodePrefixRegistry() {
        this.prefixesByModules = new HashMap<Module, String>();
        
        this.prefixesByModules.put(Module.conventions, "001");
        this.prefixesByModules.put(Module.dailyplanpanel, "002");
        this.prefixesByModules.put(Module.debugids, "003");
        this.prefixesByModules.put(Module.editingprocesspanel, "004");
        this.prefixesByModules.put(Module.embeddedfilereading, "005");
        this.prefixesByModules.put(Module.estimatedcompletiontimespanel, "006");
        this.prefixesByModules.put(Module.export2tj3, "007");
        this.prefixesByModules.put(Module.injectorfactory, "008");
        this.prefixesByModules.put(Module.jruby, "009");
        this.prefixesByModules.put(Module.mainprocesseditingpanel, "010");
        this.prefixesByModules.put(Module.mainwindow, "011");
        this.prefixesByModules.put(Module.model, "012");
        this.prefixesByModules.put(Module.persistence, "013");
        this.prefixesByModules.put(Module.processpanel, "014");
        this.prefixesByModules.put(Module.projectscheduler, "015");
        this.prefixesByModules.put(Module.schedulingpanel, "016");
        this.prefixesByModules.put(Module.tj3bookingsparser, "017");
        this.prefixesByModules.put(Module.tj3deadlinesparser, "018");
        this.prefixesByModules.put(Module.workerpanel, "019");
        this.prefixesByModules.put(Module.version, "020");
        this.prefixesByModules.put(Module.culture2lang, "021");
        this.prefixesByModules.put(Module.entrywindow, "022");
        this.prefixesByModules.put(Module.parameterdatareader, "023");

    }
}
