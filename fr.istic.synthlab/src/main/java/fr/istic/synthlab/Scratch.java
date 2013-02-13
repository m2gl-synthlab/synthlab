package fr.istic.synthlab;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Scratch {

    public static void main(String[] arguments) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Toolbar with Popup Menu demo");

                final JToolBar toolBar = new JToolBar();
                toolBar.add(createMoreButton());

                final JPanel panel = new JPanel(new BorderLayout());
                panel.add(toolBar, BorderLayout.NORTH);
                panel.setPreferredSize(new Dimension(600, 400));
                frame.getContentPane().add(panel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    private static AbstractButton createMoreButton() {
        final JToggleButton moreButton = new JToggleButton("More...");
        moreButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    createAndShowMenu((JComponent) e.getSource(), moreButton);
                }
            }
        });
        moreButton.setFocusable(false);
        moreButton.setHorizontalTextPosition(SwingConstants.LEADING);
        return moreButton;
    }

    private static void createAndShowMenu(final JComponent component, final AbstractButton moreButton) {
        JPopupMenu menu = new JPopupMenu();
        menu.add(new JMenuItem("Black"));
        menu.add(new JMenuItem("Red"));

        menu.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                moreButton.setSelected(false);
            }

            public void popupMenuCanceled(PopupMenuEvent e) {
                moreButton.setSelected(false);
            }
        });

        menu.show(component, 0, component.getHeight());
    }
}