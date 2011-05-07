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
package at.silverstrike.pcc.api.pcc;

import java.util.Map;

import ru.altruix.commons.api.fbprefixes.AbstractMessageCodePrefixRegistry;

public final class PccMessageCodePrefixRegistry extends
        AbstractMessageCodePrefixRegistry<PccFunctionalBlock> {
    private static volatile PccMessageCodePrefixRegistry instance;

    public static PccMessageCodePrefixRegistry getInstance() {
        if (instance == null) {
            instance = new PccMessageCodePrefixRegistry();
        }
        return instance;
    }

    @Override
    protected void fillPrefixesByModules(
            final Map<PccFunctionalBlock, String> aPrefixesByFunctionalBlocks) {
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.conventions, "001");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.dailyplanpanel,
                "002");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.debugids, "003");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.editingprocesspanel,
                "004");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.embeddedfilereading,
                "005");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.estimatedcompletiontimespanel, "006");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.export2tj3, "007");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.injectorfactory,
                "008");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.jruby, "009");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.mainprocesseditingpanel,
                "010");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.mainwindow, "011");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.model, "012");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.persistence, "013");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.processpanel, "014");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.projectscheduler,
                "015");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.schedulingpanel,
                "016");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.tj3bookingsparser,
                "017");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.tj3deadlinesparser,
                "018");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.workerpanel, "019");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.version, "020");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.culture2lang, "021");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.entrywindow, "022");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.parameterdatareader,
                "023");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.openid, "024");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.centraleditingpanel,
                "025");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.dependencieseditingwindow,
                "026");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.graphdemopanel,
                "027");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.taskeditingpanel,
                "028");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.eventeditingpanel,
                "029");
        aPrefixesByFunctionalBlocks
                .put(PccFunctionalBlock.milestoneeditingpanel, "030");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.projectnetworkgraphpanel,
                "031");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.graph2resource,
                "032");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.mainwindowcontroller, "033");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.xmlserialization,
                "034");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.centraleditingpanelcontroller, "035");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.dependencieseditingwindowcontroller,
                "036");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.eventeditingpanelcontroller, "037");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.milestoneeditingpanelcontroller,
                "038");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.projectnetworkgraphcreator,
                "039");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.projectnetworkgraphpanelcontroller,
                "040");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.projecttreemodel,
                "041");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.taskeditingpanelcontroller,
                "042");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.testtablecreator,
                "043");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.validation, "044");
        aPrefixesByFunctionalBlocks.put(PccFunctionalBlock.webguibus, "045");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.centraleditingpanelbuttonstate, "046");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.dependencieseditingdialog,
                "047");
        aPrefixesByFunctionalBlocks.put(
                PccFunctionalBlock.dependencieseditingpanel,
                "048");
    }
}
