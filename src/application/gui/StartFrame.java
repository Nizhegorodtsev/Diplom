package application.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class StartFrame extends JFrame
{
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	public StartFrame()
	{
		super("�������� ����");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font font = new Font("Verdana", Font.PLAIN, 18);

		String[] items = { "������ ��������� ��������" };

		Container content = getContentPane();

		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		final JLabel label = new JLabel(" ");
		label.setAlignmentX(LEFT_ALIGNMENT);
		label.setFont(font);
		label.setPreferredSize(new Dimension(100, 50));
		content.add(label);

		ActionListener actionListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JComboBox box = (JComboBox) e.getSource();
				String item = (String) box.getSelectedItem();
				label.setText(item);
			}
		};

		JComboBox comboBox = new JComboBox(items);
		comboBox.setFont(font);
		comboBox.setAlignmentX(LEFT_ALIGNMENT);
		comboBox.addActionListener(actionListener);
		comboBox.setPreferredSize(new Dimension(100, 50));
		content.add(comboBox);

		setPreferredSize(new Dimension(1000, 500));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				StartFrame ex = new StartFrame();
				ex.setVisible(true);
			}
		});
	}

}
