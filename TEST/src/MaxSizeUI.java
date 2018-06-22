import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MaxSizeUI {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MaxSizeUI().makeUI();
			}
		});
	}

	public void makeUI() {
		// 确保一个漂亮的外观风格
		JFrame.setDefaultLookAndFeelDecorated(true);
		final JFrame frame = new JFrame("我爱你路路") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Dimension d = getSize();
				Dimension m = getMaximumSize();
				boolean resize = d.width > m.width || d.height > m.height;
				d.width = Math.min(m.width, d.width);
				d.height = Math.min(m.height, d.height);
				if (resize) {
					Point p = getLocation();
					setVisible(false);
					setSize(d);
					setLocation(p);
					setVisible(true);
				}
				super.paint(g);
			}

			@Override
			protected void processWindowEvent(WindowEvent e) {
				if (e.getID() == WindowEvent.WINDOW_CLOSING) {
					JOptionPane.showMessageDialog(this, "Hello Information Message", "消息标题",
							JOptionPane.INFORMATION_MESSAGE);

					return; // 直接返回，阻止默认动作，阻止窗口关闭
				}
				super.processWindowEvent(e); // 该语句会执行窗口事件的默认动作(如：隐藏)
			}
		};

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		frame.setMaximumSize(new Dimension(800, 400));// 设置最大值
		frame.setMinimumSize(new Dimension(400, 200));// 设置最小值
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}