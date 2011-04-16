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

import java.util.Map;

import ru.altruix.commons.api.fbprefixes.AbstractMessageCodePrefixRegistry;

import at.silverstrike.pcc.api.pcc.FunctionalBlock;

public final class PccMessageCodePrefixRegistry extends AbstractMessageCodePrefixRegistry<FunctionalBlock> {
    private static volatile PccMessageCodePrefixRegistry instance;

    public static PccMessageCodePrefixRegistry getInstance() {
        if (instance == null) {
            instance = new PccMessageCodePrefixRegistry();
        }
        return instance;
    }

    @Override
    protected void fillPrefixesByModules(
            final Map<FunctionalBlock, String> aPrefixesByFunctionalBlocks) {
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.conventions, "001");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.dailyplanpanel, "002");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.debugids, "003");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.editingprocesspanel, "004");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.embeddedfilereading, "005");
        aPrefixesByFunctionalBlocks.put(
                FunctionalBlock.estimatedcompletiontimespanel, "006");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.export2tj3, "007");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.injectorfactory, "008");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.jruby, "009");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.mainprocesseditingpanel,
                "010");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.mainwindow, "011");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.model, "012");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.persistence, "013");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.processpanel, "014");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.projectscheduler, "015");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.schedulingpanel, "016");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.tj3bookingsparser, "017");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.tj3deadlinesparser, "018");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.workerpanel, "019");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.version, "020");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.culture2lang, "021");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.entrywindow, "022");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.parameterdatareader, "023");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.openid, "024");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.centraleditingpanel, "025");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.dependencieseditingwindow,
                "026");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.graphdemopanel, "027");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.taskeditingpanel, "028");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.eventeditingpanel, "029");
        aPrefixesByFunctionalBlocks
                .put(FunctionalBlock.milestoneeditingpanel, "030");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.projectnetworkgraphpanel,
                "031");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.graph2resource, "032");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.mainwindowcontroller, "033");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.xmlserialization, "034");
        aPrefixesByFunctionalBlocks.put(
                FunctionalBlock.centraleditingpanelcontroller, "035");
        aPrefixesByFunctionalBlocks.put(
                FunctionalBlock.dependencieseditingwindowcontroller,
                "036");
        aPrefixesByFunctionalBlocks.put(
                FunctionalBlock.eventeditingpanelcontroller, "037");
        aPrefixesByFunctionalBlocks.put(
                FunctionalBlock.milestoneeditingpanelcontroller,
                "038");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.projectnetworkgraphcreator,
                "039");
        aPrefixesByFunctionalBlocks.put(
                FunctionalBlock.projectnetworkgraphpanelcontroller,
                "040");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.projecttreemodel, "041");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.taskeditingpanelcontroller,
                "042");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.testtablecreator, "043");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.validation, "044");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.webguibus, "045");
        aPrefixesByFunctionalBlocks.put(
                FunctionalBlock.centraleditingpanelbuttonstate, "046");
        aPrefixesByFunctionalBlocks.put(FunctionalBlock.dependencieseditingdialog,
                "047");
    }

}
