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

package at.silverstrike.pcc.impl.graphdemopanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jgraph.JGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.conventions.SingleActivityModule;

/**
 * @author DP118M
 * 
 */
class JGraphDemo implements SingleActivityModule {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(JGraphDemo.class);

    @Override
    public void run() throws PccException {
        final ListenableGraph graph =
                new ListenableDirectedGraph(DefaultEdge.class);
        final JGraphModelAdapter graphModelAdapter =
                new JGraphModelAdapter(graph);
        final JGraph jgraph = new JGraph(graphModelAdapter);

        graph.addVertex("IE");
        graph.addVertex("P1");
        graph.addVertex("P2");
        graph.addVertex("P3");
        graph.addVertex("FE");

        graph.addEdge("IE", "P1");
        graph.addEdge("P1", "P2");
        graph.addEdge("P2", "FE");
        graph.addEdge("IE", "P3");
        graph.addEdge("P3", "FE");

        final BufferedImage bufferedImage =
                new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);

        final Graphics2D graphics2d = bufferedImage.createGraphics();

        jgraph.paint(graphics2d);

        try {
            ImageIO.write(bufferedImage, "PNG", new File("graph.PNG"));
        } catch (final IOException exception) {
            LOGGER.error("", exception);
        }
    }

}
