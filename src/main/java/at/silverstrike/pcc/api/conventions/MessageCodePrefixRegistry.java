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

    private Map<FunctionalBlock, String> prefixesByModules;

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

    public String getPrefix(final FunctionalBlock aModule) {
        return this.prefixesByModules.get(aModule)
                + PREFIX_MESSAGE_NUMBER_SEPARATOR;
    }

    private MessageCodePrefixRegistry() {
        this.prefixesByModules = new HashMap<FunctionalBlock, String>();

        this.prefixesByModules.put(FunctionalBlock.conventions, "001");
        this.prefixesByModules.put(FunctionalBlock.dailyplanpanel, "002");
        this.prefixesByModules.put(FunctionalBlock.debugids, "003");
        this.prefixesByModules.put(FunctionalBlock.editingprocesspanel, "004");
        this.prefixesByModules.put(FunctionalBlock.embeddedfilereading, "005");
        this.prefixesByModules.put(FunctionalBlock.estimatedcompletiontimespanel, "006");
        this.prefixesByModules.put(FunctionalBlock.export2tj3, "007");
        this.prefixesByModules.put(FunctionalBlock.injectorfactory, "008");
        this.prefixesByModules.put(FunctionalBlock.jruby, "009");
        this.prefixesByModules.put(FunctionalBlock.mainprocesseditingpanel, "010");
        this.prefixesByModules.put(FunctionalBlock.mainwindow, "011");
        this.prefixesByModules.put(FunctionalBlock.model, "012");
        this.prefixesByModules.put(FunctionalBlock.persistence, "013");
        this.prefixesByModules.put(FunctionalBlock.processpanel, "014");
        this.prefixesByModules.put(FunctionalBlock.projectscheduler, "015");
        this.prefixesByModules.put(FunctionalBlock.schedulingpanel, "016");
        this.prefixesByModules.put(FunctionalBlock.tj3bookingsparser, "017");
        this.prefixesByModules.put(FunctionalBlock.tj3deadlinesparser, "018");
        this.prefixesByModules.put(FunctionalBlock.workerpanel, "019");
        this.prefixesByModules.put(FunctionalBlock.version, "020");
        this.prefixesByModules.put(FunctionalBlock.culture2lang, "021");
        this.prefixesByModules.put(FunctionalBlock.entrywindow, "022");
        this.prefixesByModules.put(FunctionalBlock.parameterdatareader, "023");
        this.prefixesByModules.put(FunctionalBlock.openid, "024");
        this.prefixesByModules.put(FunctionalBlock.centraleditingpanel, "025");
        this.prefixesByModules.put(FunctionalBlock.dependencieseditingwindow, "026");
        this.prefixesByModules.put(FunctionalBlock.graphdemopanel, "027");
        this.prefixesByModules.put(FunctionalBlock.taskeditingpanel, "028");
        this.prefixesByModules.put(FunctionalBlock.meetingeditingpanel, "029");
        this.prefixesByModules.put(FunctionalBlock.milestoneeditingpanel, "030");
        this.prefixesByModules.put(FunctionalBlock.projectnetworkgraphpanel, "031");
        this.prefixesByModules.put(FunctionalBlock.graph2resource, "032");
        this.prefixesByModules.put(FunctionalBlock.mainwindowcontroller, "033");
        this.prefixesByModules.put(FunctionalBlock.xmlserialization, "034");
        this.prefixesByModules.put(FunctionalBlock.centraleditingpanelcontroller, "035");
        this.prefixesByModules.put(FunctionalBlock.dependencieseditingwindowcontroller,
                "036");
        this.prefixesByModules.put(FunctionalBlock.meetingeditingpanelcontroller, "037");
        this.prefixesByModules.put(FunctionalBlock.milestoneeditingpanelcontroller,
                "038");
        this.prefixesByModules.put(FunctionalBlock.projectnetworkgraphcreator, "039");
        this.prefixesByModules.put(FunctionalBlock.projectnetworkgraphpanelcontroller,
                "040");
        this.prefixesByModules.put(FunctionalBlock.projecttreemodel, "041");
        this.prefixesByModules.put(FunctionalBlock.taskeditingpanelcontroller, "042");
        this.prefixesByModules.put(FunctionalBlock.testtablecreator, "043");
        this.prefixesByModules.put(FunctionalBlock.validation, "044");
        this.prefixesByModules.put(FunctionalBlock.webguibus, "045");
    }
}
