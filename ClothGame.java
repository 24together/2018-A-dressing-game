package clothGame2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ClothGame extends JFrame {
//������Ʈ ����
	private JFrame LoadFrame = new JFrame();
	private JFrame BigFrame = new JFrame();
	private JPanel LoadPanel = new JPanel();
	private JPanel BigPanel = new JPanel();
	private JLabel requestLabel = new JLabel();
	private JLabel Logo = new JLabel(), Yger = new JLabel(), WDYger = new JLabel("WDYger"),
			CPYger = new JLabel("CPYger"), SGYger = new JLabel("SGYger");
	private JLabel LoadYger = new JLabel(), LoadLogo = new JLabel(), ClothGame = new JLabel(), loadBar;
	private JTextArea WDyTA = new JTextArea("��/�����ͺ��̽� ����"), CPyTA = new JTextArea("��ǻ�����α׷��� ����"),
			SGyTA = new JTextArea("����Ʈ ���������� ����");
	private JButton reset = new JButton("RESET");
	public JTextArea impor = new JTextArea();
	public String requestField = "";
	private boolean DraggedBool=false;
	int offX, offY;
	public String imporField = "", tempName = "";
	ImageIcon img = null;

//������ ����
	DraggedListener draggedlistener = new DraggedListener();
	MyMouse clicklistener = new MyMouse();
	MyListener resetListener = new MyListener();
//���� ������
	class Request extends Thread {
		public String request[] = { "��", "��", "�� ", "��", "��", "��", "Ʈ ", "��", "ȣ", "��", "�� ", "��", "�� ", "��", "��", "��",
				"��", "��", "!", "!" };

		public void run() {
			try {
				Thread.sleep(3300);
			} catch (InterruptedException e) {
			}
			for (int i = 0; i < request.length; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					return;
				}
				requestField += request[i];
				requestLabel.setText(requestField);
			}
		}
	}
//��������(���̰Ű� �����ϴ� �κ�) ������
	class imporText extends Thread {
		public void run() {
			impor.setText("");
			imporField = "";
			String tempString[] = new String[4];
			if (tempName == "") {
				try {
					Thread.sleep(3500);
				} catch (InterruptedException e) {
				}
				tempString = yy.getOpening();
			}
			if (tempName == "WDYger")
				tempString = yy.getWDimpor();
			if (tempName == "CPYger")
				tempString = yy.getCPimpor();
			if (tempName == "SGYger")
				tempString = yy.getSGimpor();
			for (int i = 0; i < tempString.length; i++) {
				try {
					Thread.sleep(700);
				} catch (InterruptedException e) {
					return;
				}
				imporField += tempString[i] + "\n";
				impor.setText(imporField);
			}
		}
	}

//YYGer�ν��Ͻ�ȭ
	YYger yy = new YYger();

	public ClothGame(YYger yy) {
//������ ����
		LoadFrame.setTitle("���̰� �������� ����(��ǻ�� �����迭 ver)");
		BigFrame.setTitle("���̰� �������� ����(��ǻ�� �����迭 ver)");
		LoadFrame.setSize(1340, 700);
		BigFrame.setSize(1340, 700);
//������������ �г�
		/***************** BigPanel *****************/
		BigPanel.setBackground(Color.WHITE);
		impor.setBackground(new Color(230, 255, 255));
		reset.setBackground(Color.WHITE);
		BigFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BigPanel.setLayout(null);
		LoadYger.setLayout(null);
		BigPanel.add(reset);
		BigPanel.add(requestLabel);
		BigPanel.add(WDyTA);
		BigPanel.add(CPyTA);
		BigPanel.add(SGyTA);
		BigPanel.add(reset);
		BigPanel.add(impor);
		(new Request()).start();

		requestLabel.setFont(new Font("���� ���", Font.BOLD, 30));
		impor.setFont(new Font("���� ���", Font.PLAIN, 15));
		reset.setFont(new Font("���� ���", Font.BOLD, 18));
	//���� ����
		try {
			ImageIcon logo = new ImageIcon("img/logo.png");
			Logo.setIcon(logo);
			ImageIcon loadlogo = new ImageIcon("img/logo.png");
			LoadLogo.setIcon(loadlogo);
			ImageIcon yger = new ImageIcon("img/���̰�.png");
			Yger.setIcon(yger);
			ImageIcon wdger = new ImageIcon("img/WD���̰�.png");
			WDYger.setIcon(wdger);
			ImageIcon cpger = new ImageIcon("img/CP���̰�.png");
			CPYger.setIcon(cpger);
			ImageIcon sgger = new ImageIcon("img/SG���̰�.png");
			SGYger.setIcon(sgger);

		} catch (NullPointerException e) {
			requestField = "������ �����ϴ�. ������ġ�� �ٽ� Ȯ���� �ּ���";
			requestLabel.setText(requestField);
		}
		(new imporText()).start();
	//������ �Է�
		reset.addActionListener(resetListener);

		WDYger.addMouseListener(clicklistener);
		CPYger.addMouseListener(clicklistener);
		SGYger.addMouseListener(clicklistener);

		WDYger.addMouseMotionListener(draggedlistener);
		CPYger.addMouseMotionListener(draggedlistener);
		SGYger.addMouseMotionListener(draggedlistener);
	//���̾ƿ�
		WDYger.setLayout(null);
		CPYger.setLayout(null);
		SGYger.setLayout(null);
		WDYger.setBounds(440, 120, 241, 285);
		CPYger.setBounds(730, 120, 241, 285);
		SGYger.setBounds(1040, 120, 241, 285);
		reset.setBounds(730, 500, 180, 50);

		Logo.setBounds(0, 10, 500, 50);
		Yger.setBounds(80, 120, 241, 285);

		requestLabel.setBounds(340, 60, 1000, 40);
		impor.setBounds(20, 420, 400, 200);
		WDyTA.setBounds(480, 420, 150, 30);
		CPyTA.setBounds(770, 420, 150, 30);
		SGyTA.setBounds(1080, 420, 150, 30);
	//���̺� �߰�
		BigPanel.add(Logo);
		BigPanel.add(WDYger);
		BigPanel.add(CPYger);
		BigPanel.add(SGYger);
		BigPanel.add(Yger);
//�ε������� �г�
		/***************** LoadPanel ***************/
		LoadPanel.setBackground(Color.WHITE);
		LoadFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoadPanel.setLayout(null);
	//������ ������Ʈ �߰�
		loadBar = new JLabel() {ImageIcon i = new ImageIcon("img/loadBar.gif");
			public void paintComponent(Graphics g) {
				g.drawImage(i.getImage(),0, 0, 604, 56, null);
				repaint();
			}
		};
		
		LoadFrame.add(loadBar);
		loadBar.setBounds(380, 500,604,56);
		LoadLogo.setBounds(320, 420, 414, 56);
		LoadPanel.add(LoadLogo);
		LoadFrame.add(LoadYger);
		LoadFrame.add(ClothGame);

		LoadFrame.add(LoadPanel);
		BigFrame.add(BigPanel);

		LoadFrame.setVisible(true);

		ImageIcon loadyger = new ImageIcon("img/load���̰�.png");
		ImageIcon clothgame = new ImageIcon("img/clothgame.png");
	//���������� ���̺��� ������ ȿ��
		try {
			Thread.sleep(1000);
			ClothGame.setIcon(clothgame);
			ClothGame.setBounds(730, 420, 414, 50);
			Thread.sleep(1000);
			LoadYger.setIcon(loadyger);
			LoadYger.setBounds(580, 120, 241, 285);
			Thread.sleep(1300);
		} catch (InterruptedException e) {
			System.out.println("����");
		}
//�ε������Ӱ� ������������ ����
		LoadFrame.setVisible(false);
		BigFrame.setVisible(true);
	}// end of ClothGame method
//���� ��ư ���� ������
	private class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			WDYger.setBounds(440, 120, 241, 285);
			CPYger.setBounds(730, 120, 241, 285);
			SGYger.setBounds(1040, 120, 241, 285);
			impor.setText("�� ������ �˷��ٱ�?");
		}
	}
//�������� ���̺� ���� ������
	private class MyMouse implements MouseListener {

		public void mousePressed(MouseEvent e) {
			JLabel tempLabel = (JLabel)e.getSource();
			if(tempLabel.contains(new Point(e.getX(),e.getY()))) {
				
				offX = e.getX()-tempLabel.getX();
				offY = e.getY()-tempLabel.getY();
				DraggedBool = true;
			}
		}//��ġ�� �޾� �ٽñ׷��ش�

		public void mouseReleased(MouseEvent e) {
			
			DraggedBool= false;
			impor.setText("�� ������ �˷��ٱ�?");
			JLabel tempLabel = (JLabel) e.getSource();
			int x=e.getX()-offX;
			tempName = tempLabel.getText();// tempLabel = null�� ��Ȳ

			System.out.println(tempName);
			if (x <= 300) {

				tempLabel.setBounds(80, 120, 241, 285);

				(new imporText()).start();
			}
		}//x�� 300 ���ϸ� ���� ����� �Ǵ� ��ġ�� setBound

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}
//�巡�� �� ��ġ�� �ٲ��
	private class DraggedListener implements MouseMotionListener {

	public void mouseDragged(MouseEvent e) {
			if(DraggedBool) {
			JLabel tempLabel = (JLabel) e.getSource();
			int x = e.getX()-offX;
			int y = e.getY()-offY;
			System.out.println("x="+x+",y="+y);
			tempLabel.setBounds(x,y,241,285);
			}
		}
		public void mouseMoved(MouseEvent e) {

		}
	}
//���� �޼ҵ�
}
