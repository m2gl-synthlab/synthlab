package fr.istic.synthlab.presentation.module.keyboard;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.istic.synthlab.controller.module.keyboard.ICModuleKeyboard;
import fr.istic.synthlab.presentation.module.APModule;

public class PModuleKeyboard extends APModule implements IPModuleKeyboard {

	private static final long serialVersionUID = -1169985238707648442L;

	private ICModuleKeyboard ctrl;

	private JButton doBtn;
	private JButton doDBtn;
	private JButton reBtn;
	private JButton reDBtn;
	private JButton miBtn;
	private JButton faDBtn;
	private JButton faBtn;
	private JButton solBtn;
	private JButton solDBtn;
	private JButton laBtn;
	private JButton laDBtn;
	private JButton siBtn;

	public PModuleKeyboard(ICModuleKeyboard control) {
		super(control);
		ctrl = control;

		configView();
		defineCallbacks();
	}

	private void configView() {
		doBtn = new JButton();
		doBtn.setBackground(Color.WHITE);
		doBtn.setLocation(0 * 20, 0);
		doBtn.setSize(20, 120);
		add(doBtn, 0, -1);

		reBtn = new JButton();
		reBtn.setBackground(Color.WHITE);
		reBtn.setLocation(1 * 20, 0);
		reBtn.setSize(20, 120);
		add(reBtn, 0, -1);

		miBtn = new JButton();
		miBtn.setBackground(Color.WHITE);
		miBtn.setLocation(2 * 20, 0);
		miBtn.setSize(20, 120);
		add(miBtn, 0, -1);

		faBtn = new JButton();
		faBtn.setBackground(Color.WHITE);
		faBtn.setLocation(3 * 20, 0);
		faBtn.setSize(20, 120);
		add(faBtn, 0, -1);

		solBtn = new JButton();
		solBtn.setBackground(Color.WHITE);
		solBtn.setLocation(4 * 20, 0);
		solBtn.setSize(20, 120);
		add(solBtn, 0, -1);

		laBtn = new JButton();
		laBtn.setBackground(Color.WHITE);
		laBtn.setLocation(5 * 20, 0);
		laBtn.setSize(20, 120);
		add(laBtn, 0, -1);

		siBtn = new JButton();
		siBtn.setBackground(Color.WHITE);
		siBtn.setLocation(6 * 20, 0);
		siBtn.setSize(20, 120);
		add(siBtn, 0, -1);

		// BLACK
		doDBtn = new JButton();
		doDBtn.setBackground(Color.BLACK);
		doDBtn.setLocation(0 * 20 + 12, 0);
		doDBtn.setSize(16, 80);
		add(doDBtn, 1, -1);

		reDBtn = new JButton();
		reDBtn.setBackground(Color.BLACK);
		reDBtn.setLocation(1 * 20 + 12, 0);
		reDBtn.setSize(16, 80);
		add(reDBtn, 1, -1);

		faDBtn = new JButton();
		faDBtn.setBackground(Color.BLACK);
		faDBtn.setLocation(3 * 20 + 12, 0);
		faDBtn.setSize(16, 80);
		add(faDBtn, 1, -1);

		solDBtn = new JButton();
		solDBtn.setBackground(Color.BLACK);
		solDBtn.setLocation(4 * 20 + 12, 0);
		solDBtn.setSize(16, 80);
		add(solDBtn, 1, -1);

		laDBtn = new JButton();
		laDBtn.setBackground(Color.BLACK);
		laDBtn.setLocation(5 * 20 + 12, 0);
		laDBtn.setSize(16, 80);
		add(laDBtn, 1, -1);

	}

	private void defineCallbacks() {
		doBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("do");
			}
		});
		doDBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("doD");
			}
		});
		reBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("re");
			}
		});
		reDBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("reD");
			}
		});
		miBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("mi");
			}
		});
		faDBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("fa");
			}
		});
		faBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("faD");
			}
		});
		solBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("sol");
			}
		});
		solDBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("solD");
			}
		});
		laBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("la");
			}
		});
		laDBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("laD");
			}
		});
		siBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("si");
			}
		});
	}

	@Override
	public ICModuleKeyboard getControl() {
		return (ICModuleKeyboard) ctrl;
	}

}
