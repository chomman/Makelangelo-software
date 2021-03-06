package com.marginallyclever.artPipeline.generators;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.marginallyclever.makelangelo.Translator;
import com.marginallyclever.makelangelo.select.SelectFloat;
import com.marginallyclever.makelangelo.select.SelectInteger;

public class Generator_LSystemTree_Panel extends ImageGeneratorPanel implements DocumentListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SelectInteger field_order;
	SelectInteger field_branches;
	SelectFloat field_orderScale;
	SelectFloat field_angle;
	Generator_LSystemTree generator;
	
	
	Generator_LSystemTree_Panel(Generator_LSystemTree generator) {
		this.generator = generator;

		field_order = new SelectInteger(generator.getOrder());
		field_branches = new SelectInteger(generator.getBranches());
		field_orderScale = new SelectFloat((float)generator.getScale());
		field_angle = new SelectFloat((float)generator.getAngle());

		setLayout(new GridLayout(0, 1));
		add(new JLabel(Translator.get("HilbertCurveOrder")));
		add(field_order);
		add(new JLabel(Translator.get("LSystemBranches")));
		add(field_branches);
		add(new JLabel(Translator.get("LSystemOrderScale")));
		add(field_orderScale);
		add(new JLabel(Translator.get("LSystemAngle")));
		add(field_angle);
		
		field_order.getDocument().addDocumentListener(this);
		field_branches.getDocument().addDocumentListener(this);
		field_orderScale.getDocument().addDocumentListener(this);
		field_angle.getDocument().addDocumentListener(this);
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		validate();
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		validate();
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		validate();
	}
	
	@Override
	public void validate() {
		generator.setOrder(((Number)field_order.getValue()).intValue());
		generator.setBranches(((Number)field_branches.getValue()).intValue());
		generator.setScale(((Number)field_orderScale.getValue()).doubleValue());
		generator.setAngle(((Number)field_angle.getValue()).doubleValue());
		makelangeloRobotPanel.regenerate(generator);
	}
}
