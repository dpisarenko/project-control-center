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
package at.silverstrike.pcc.api.conventions;

import java.util.HashMap;
import java.util.Map;

public final class MessageCodePrefixRegistry {
    private static final String PREFIX_MESSAGE_NUMBER_SEPARATOR = ".";

    private Map<Module, String> prefixesByModules;

    private static volatile MessageCodePrefixRegistry instance;

    public static MessageCodePrefixRegistry getInstance() {
        if (instance == null) {
            instance = new MessageCodePrefixRegistry();
        }
        return instance;
    }

    public static String getMessageNumberSeparator() {
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
        this.prefixesByModules.put(Module.openid, "024");
        this.prefixesByModules.put(Module.centraleditingpanel, "025");
        this.prefixesByModules.put(Module.dependencieseditingwindow, "026");
        this.prefixesByModules.put(Module.graphdemopanel, "027");
        this.prefixesByModules.put(Module.taskeditingpanel, "028");
        this.prefixesByModules.put(Module.meetingeditingpanel, "029");
        this.prefixesByModules.put(Module.milestoneeditingpanel, "030");
        this.prefixesByModules.put(Module.projectnetworkgraphpanel, "031");
        this.prefixesByModules.put(Module.graph2resource, "032");
        this.prefixesByModules.put(Module.mainwindowcontroller, "033");
        this.prefixesByModules.put(Module.xmlserialization, "034");
        this.prefixesByModules.put(Module.centraleditingpanelcontroller, "035");
        this.prefixesByModules.put(Module.dependencieseditingwindowcontroller,
                "036");
        this.prefixesByModules.put(Module.meetingeditingpanelcontroller, "037");
        this.prefixesByModules.put(Module.milestoneeditingpanelcontroller,
                "038");
        this.prefixesByModules.put(Module.projectnetworkgraphcreator, "039");
        this.prefixesByModules.put(Module.projectnetworkgraphpanelcontroller,
                "040");
        this.prefixesByModules.put(Module.projecttreemodel, "041");
        this.prefixesByModules.put(Module.taskeditingpanelcontroller, "042");
        this.prefixesByModules.put(Module.testtablecreator, "043");
        this.prefixesByModules.put(Module.validation, "044");
        this.prefixesByModules.put(Module.webguibus, "045");
    }
}
