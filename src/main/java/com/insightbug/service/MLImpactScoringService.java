package com.insightbug.service;

import org.springframework.stereotype.Service;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.InputStream;
import java.util.ArrayList;

@Service
public class MLImpactScoringService {

    private LinearRegression model;
    private ArrayList<Attribute> attributes;

    public MLImpactScoringService() {
        setupAttributes();
        trainModel();
    }

    private void setupAttributes() {
        attributes = new ArrayList<>();
        attributes.add(new Attribute("titleLength"));
        attributes.add(new Attribute("stackTraceLength"));
        attributes.add(new Attribute("userReports"));
        attributes.add(new Attribute("impact"));  // target variable
    }

    private void trainModel() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("impact_training.arff");
            DataSource source = new DataSource(inputStream);
            Instances data = source.getDataSet();
            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);

            model = new LinearRegression();
            model.buildClassifier(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double scoreBug(String title, String description) {
        // Simple heuristic or basic ML placeholder
        double score = 0.0;

        if (title.toLowerCase().contains("login")) score += 5;
        if (description.toLowerCase().contains("payment")) score += 8;
        if (description.toLowerCase().contains("timeout")) score += 3;
        if (title.toLowerCase().contains("error")) score += 2;

        return Math.min(score, 10.0); // Max cap
    }
}
