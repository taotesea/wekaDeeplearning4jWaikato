/*
 * WekaDeeplearning4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WekaDeeplearning4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WekaDeeplearning4j.  If not, see <https://www.gnu.org/licenses/>.
 *
 * LeNet.java
 * Copyright (C) 2017-2018 University of Waikato, Hamilton, New Zealand
 */

package weka.dl4j.zoo;

import org.deeplearning4j.nn.conf.CacheMode;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.zoo.PretrainedType;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.dl4j.Preferences;

/**
 * A WEKA version of DeepLearning4j's LeNet ZooModel.
 *
 * @author Steven Lang
 */
public class LeNet extends AbstractZooModel {

  // TODO get pretrained weights working

  private static final long serialVersionUID = 79837621346455139L;

  @Override
  public LeNet setPretrainedType(PretrainedType pretrainedType) {
    return (LeNet) setPretrainedType(pretrainedType,
            500,
            "7",
            "8",
            new String[] {"9"});
  }

  @Override
  public ComputationGraph init(int numLabels, long seed, int[] shape) {
    org.deeplearning4j.zoo.model.LeNet net = org.deeplearning4j.zoo.model.LeNet.builder()
        .cacheMode(CacheMode.NONE)
        .workspaceMode(Preferences.WORKSPACE_MODE)
        .inputShape(shape)
        .numClasses(numLabels)
        .build();

    ComputationGraph defaultNet = ((MultiLayerNetwork) net.init()).toComputationGraph();

    return attemptToLoadWeights(net, defaultNet, seed, numLabels);
  }

  @Override
  public int[][] getShape() {
    return org.deeplearning4j.zoo.model.LeNet.builder().build().metaData().getInputShape();
  }
}
