package com.gui;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Gui extends JFrame {

    private JRelativeLayout rootLayout;

    private JPanel pA, pB;

    private JRelativeLayout aLayout;
    private JComboBox<String> categories;
    private JScrollPane itemsScrollPane;
    private JList<String> items;
    private JTextField textField;

    private JRelativeLayout bLayout;
    private JScrollPane canvasScrollPane;
    private JMapEditorCanvas canvas;

    public Gui() throws IOException {
        setSize(800, 800);
        //setExtendedState(Frame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);

        pA = new JPanel();
        pA.setName("pA");
        pA.setFocusable(false);

        pB = new JPanel();
        pB.setName("pB");
        pB.setFocusable(false);

        categories = new JComboBox<>();
        categories.setName("categories");
        categories.setFocusable(false);
        setupCategories(new String[]{"GROUND", "TOWN", "NATURE", "INTERIOR"}, categories);

        itemsScrollPane = new JScrollPane();
        itemsScrollPane.setName("itemsPane");
        itemsScrollPane.setFocusable(false);
        items = new JList<>();
        setupItems(new String[]{"A", "B", "C", "D", "E", "F"}, items);

        textField = new JTextField();
        textField.setName("textField");

        canvas = new JMapEditorCanvas(100, 100);
        canvas.setName("canvas");
        canvas.setFocusable(true);
        canvasScrollPane = new JScrollPane(canvas);
        canvasScrollPane.setFocusable(false);
        canvasScrollPane.setName("canvasScrollPane");

        rootLayout = new JRelativeLayout();
        aLayout = new JRelativeLayout();
        bLayout = new JRelativeLayout();

        pA.setLayout(aLayout);
        pA.add(categories,
                "parentTop=true parentStart=true width=matchParent height=30");
        pA.add(itemsScrollPane,
                "bellow=categories width=matchParent height=matchParent");

        pB.setLayout(bLayout);
        pB.add(canvasScrollPane,
                "parentTop=true parentStart=true width=matchParent height=matchParent");

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println("ooooiiiii");
                }
            }
        });

        setLayout(rootLayout);
        add(pA,
                "parentTop=true parentStart=true width=10% height=matchParent");
        add(pB,
                "parentTop=true endOf=pA width=matchParent height=matchParent");
    }

    private void setupCategories(String[] content, JComboBox<String> target) {
        target.setModel(new DefaultComboBoxModel<>(content));
    }

    private void setupItems(String[] content, JList<String> target) {
        target.setModel(new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return content.length;
            }

            @Override
            public String getElementAt(int i) {
                return content[i];
            }
        });
        itemsScrollPane.setViewportView(target);
    }
}
