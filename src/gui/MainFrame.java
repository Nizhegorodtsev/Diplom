package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements ActionListener
{
    private JButton           button;
    private JButton           button2;
    private JButton           button3;
    private JPanel            topPanel;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MainFrame()
    {
	setLayout(new BorderLayout());
	createGUI();
	setSize(1000, 700);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
    }

    private void createGUI()
    {
	topPanel = new JPanel();
	topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
	add(topPanel, BorderLayout.NORTH);

	button = new JButton("Гистограммы");
	button.addActionListener(this);
	topPanel.add(button);

	button2 = new JButton("Гистограммы2");
	button.addActionListener(this);
	topPanel.add(button2);
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
	button.setText("lol " + System.currentTimeMillis());
    }
}
