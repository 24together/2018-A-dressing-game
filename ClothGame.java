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
//컴포넌트 선언
	private JFrame LoadFrame = new JFrame();
	private JFrame BigFrame = new JFrame();
	private JPanel LoadPanel = new JPanel();
	private JPanel BigPanel = new JPanel();
	private JLabel requestLabel = new JLabel();
	private JLabel Logo = new JLabel(), Yger = new JLabel(), WDYger = new JLabel("WDYger"),
			CPYger = new JLabel("CPYger"), SGYger = new JLabel("SGYger");
	private JLabel LoadYger = new JLabel(), LoadLogo = new JLabel(), ClothGame = new JLabel(), loadBar;
	private JTextArea WDyTA = new JTextArea("웹/데이터베이스 전공"), CPyTA = new JTextArea("컴퓨터프로그래밍 전공"),
			SGyTA = new JTextArea("스마트 게임콘텐츠 전공");
	private JButton reset = new JButton("RESET");
	public JTextArea impor = new JTextArea();
	public String requestField = "";
	private boolean DraggedBool=false;
	int offX, offY;
	public String imporField = "", tempName = "";
	ImageIcon img = null;

//리스너 선언
	DraggedListener draggedlistener = new DraggedListener();
	MyMouse clicklistener = new MyMouse();
	MyListener resetListener = new MyListener();
//설명 스레드
	class Request extends Thread {
		public String request[] = { "영", "진", "의 ", "마", "스", "코", "트 ", "백", "호", "에", "게 ", "옷", "을 ", "입", "혀", "주",
				"세", "요", "!", "!" };

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
//전공설명(와이거가 설명하는 부분) 스레드
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

//YYGer인스턴스화
	YYger yy = new YYger();

	public ClothGame(YYger yy) {
//프레임 선언
		LoadFrame.setTitle("와이거 옷입히기 게임(컴퓨터 정보계열 ver)");
		BigFrame.setTitle("와이거 옷입히기 게입(컴퓨터 정보계열 ver)");
		LoadFrame.setSize(1340, 700);
		BigFrame.setSize(1340, 700);
//본게임프레임 패널
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

		requestLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		impor.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		reset.setFont(new Font("맑은 고딕", Font.BOLD, 18));
	//사진 삽입
		try {
			ImageIcon logo = new ImageIcon("img/logo.png");
			Logo.setIcon(logo);
			ImageIcon loadlogo = new ImageIcon("img/logo.png");
			LoadLogo.setIcon(loadlogo);
			ImageIcon yger = new ImageIcon("img/와이거.png");
			Yger.setIcon(yger);
			ImageIcon wdger = new ImageIcon("img/WD와이거.png");
			WDYger.setIcon(wdger);
			ImageIcon cpger = new ImageIcon("img/CP와이거.png");
			CPYger.setIcon(cpger);
			ImageIcon sgger = new ImageIcon("img/SG와이거.png");
			SGYger.setIcon(sgger);

		} catch (NullPointerException e) {
			requestField = "사진이 없습니다. 저장위치를 다시 확인해 주세요";
			requestLabel.setText(requestField);
		}
		(new imporText()).start();
	//리스너 입력
		reset.addActionListener(resetListener);

		WDYger.addMouseListener(clicklistener);
		CPYger.addMouseListener(clicklistener);
		SGYger.addMouseListener(clicklistener);

		WDYger.addMouseMotionListener(draggedlistener);
		CPYger.addMouseMotionListener(draggedlistener);
		SGYger.addMouseMotionListener(draggedlistener);
	//레이아웃
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
	//레이블 추가
		BigPanel.add(Logo);
		BigPanel.add(WDYger);
		BigPanel.add(CPYger);
		BigPanel.add(SGYger);
		BigPanel.add(Yger);
//로딩프레임 패널
		/***************** LoadPanel ***************/
		LoadPanel.setBackground(Color.WHITE);
		LoadFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoadPanel.setLayout(null);
	//사진과 컴포넌트 추가
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

		ImageIcon loadyger = new ImageIcon("img/load와이거.png");
		ImageIcon clothgame = new ImageIcon("img/clothgame.png");
	//점차적으로 레이블이 나오는 효과
		try {
			Thread.sleep(1000);
			ClothGame.setIcon(clothgame);
			ClothGame.setBounds(730, 420, 414, 50);
			Thread.sleep(1000);
			LoadYger.setIcon(loadyger);
			LoadYger.setBounds(580, 120, 241, 285);
			Thread.sleep(1300);
		} catch (InterruptedException e) {
			System.out.println("오류");
		}
//로딩프레임과 본게임프레임 교차
		LoadFrame.setVisible(false);
		BigFrame.setVisible(true);
	}// end of ClothGame method
//리셋 버튼 관련 리스너
	private class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			WDYger.setBounds(440, 120, 241, 285);
			CPYger.setBounds(730, 120, 241, 285);
			SGYger.setBounds(1040, 120, 241, 285);
			impor.setText("또 무엇을 알려줄까?");
		}
	}
//옷입히기 레이블 관련 리스너
	private class MyMouse implements MouseListener {

		public void mousePressed(MouseEvent e) {
			JLabel tempLabel = (JLabel)e.getSource();
			if(tempLabel.contains(new Point(e.getX(),e.getY()))) {
				
				offX = e.getX()-tempLabel.getX();
				offY = e.getY()-tempLabel.getY();
				DraggedBool = true;
			}
		}//위치를 받아 다시그려준다

		public void mouseReleased(MouseEvent e) {
			
			DraggedBool= false;
			impor.setText("또 무엇을 알려줄까?");
			JLabel tempLabel = (JLabel) e.getSource();
			int x=e.getX()-offX;
			tempName = tempLabel.getText();// tempLabel = null인 상황

			System.out.println(tempName);
			if (x <= 300) {

				tempLabel.setBounds(80, 120, 241, 285);

				(new imporText()).start();
			}
		}//x가 300 이하면 입힌 모양이 되는 위치에 setBound

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}
//드래그 시 위치가 바뀌도록
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
//메인 메소드
}
