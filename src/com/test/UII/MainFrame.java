package com.test.UII;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTree;

import com.test.search.SongLibraryMap;
import com.test.song.SongInfos;
import com.test.UII.PlayListPanel;
import com.test.UII.PlayPanel;
import com.test.UII.SearchPanel;
import com.test.UII.ShowPanel;
import com.test.player.HigherPlayer;
import com.test.ui.tool.ButtonToolBar;
import com.test.ui.tool.IconButton;

public class MainFrame extends JFrame {
	
	private final int InitialWidth = 975;
	private final int InitialHeight = 670;
	private final Point InitialPoint;

	private final int ChangedWidth = 365;
	
	
	//public final Point InnitialPoint;
	
	private PlayPanel playPanel=null;
	private PlayListPanel playListPanel=null;
	private SearchPanel searchPanel=null;
	private ShowPanel showPanel=null;
	private ButtonToolBar toolBar=null;
	
	private SongLibraryMap<String, List<SongInfos>> songLibrary;
	private final static String savaPath = "E:/Hub/download";
	
	private IconButton jb1=null;
	
	public MainFrame()
	{
		
		
		Dimension dime= Toolkit.getDefaultToolkit().getScreenSize();
		InitialPoint= new Point((dime.width - InitialWidth) / 2,(dime.height - InitialHeight) / 2);
		
		this.setTitle("MyMusicPlayer");
		this.setSize(InitialWidth, InitialHeight);
		this.setLocation(InitialPoint);
		//����������������������߰�ť
		//jb1=new IconButton("��һ��", "icon/back.png");
		//this.add(jb1);
		
		//�������ظ����ļ���
		File savefolder = new File(savaPath);
		//�����Ѿ����ظ����Ĵ洢�ļ���
		if(!savefolder.exists())
		{
			savefolder.mkdirs();
		}
		
		//��ȡ������
		readSongLibrary();
		
		//����Ƕ������
		buildPanel();
		
		
		this.setVisible(true);
		requestFocus();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	private void buildPanel()
	{
		playPanel = new PlayPanel();
		
		//�������Ĵ�����the left of the Frame
		toolBar= new ButtonToolBar();
		
		JButton[] toolBarButtons = new JButton[]{
				new IconButton("�����б�", "icon/note.png"),
				new IconButton("�����ղ�", "icon/clouds.png"),
				new IconButton("�ҵ�����","icon/download.png"),
				new IconButton("����","icon/app.png")
		};
		
		toolBar.addButtons(toolBarButtons);
		
		playListPanel = new PlayListPanel(toolBar.getButtons(), this);
		
		searchPanel = new SearchPanel();
		
		showPanel = new ShowPanel();
		
		//���ݸ�������������
		JTree[] listTree = playListPanel.deliverTree();
		
		HigherPlayer player =playPanel.getHigherPlayer();
		
		//��ͨ�������͸����б����
		playPanel.setTrees(listTree);
		player.setJTree(listTree[0]);
		playListPanel.deliverHigherPlayer(player);
		
		//��ͨ�ֿ����͸����б����
		showPanel.setListTree(listTree);
		// ��ͨ���������չʾ���
		searchPanel.setShowPanel(showPanel);
		
		// ��ͨ��������뱾��
		searchPanel.setSongLibraryMap(songLibrary);
		// ��ͨ��������������
		playPanel.setLrcPanelTextArea(showPanel.getTextArea());

		// ��ͨ�ֿ�����벥�����
		showPanel.setPlayer(player);

		playPanel.setParentFrame(this);
		
		playPanel.setPreferredSize(new Dimension(350, 115));
		playListPanel.setPreferredSize(new Dimension(305, 520));
		toolBar.setPreferredSize(new Dimension(47, 520));
		searchPanel.setPreferredSize(new Dimension(610, 115));
		showPanel.setPreferredSize(new Dimension(610, 520));
		
		buildLayout();
		
	}
	
	private void setAction() {

		// ��������¼� ��չ������
		this.addWindowStateListener(event -> {
			if (event.getNewState() == JFrame.MAXIMIZED_BOTH) {
				searchPanel.setVisible(true);
				showPanel.setVisible(true);
				setSize(InitialWidth, InitialHeight);
				setLocation(InitialPoint);
				setVisible(true);

			}
		});

		// �۵�����
		searchPanel.getCollapseButton().addActionListener(event -> {
			searchPanel.setVisible(false);
			showPanel.setVisible(false);
			setSize(ChangedWidth, InitialHeight);
			setVisible(true);
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				saveSongLibrary();

				System.exit(0);
			}
		});
	}
	
	private void buildLayout() {
		Box topBox = Box.createHorizontalBox();

		topBox.add(playPanel);
		topBox.add(searchPanel);

		Box bottomBox = Box.createHorizontalBox();
		bottomBox.add(toolBar);
		bottomBox.add(playListPanel);
		bottomBox.add(showPanel);

		Container mainPanel = getContentPane();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		mainPanel.add(topBox);
		mainPanel.add(bottomBox);
		mainPanel.add(Box.createVerticalStrut(15));
	}

	@SuppressWarnings({ "unchecked" })
	private void readSongLibrary() {

		File library = new File("E:/Hub/SongLibrary.dat");
		if (!library.exists())
			songLibrary = new SongLibraryMap<String, List<SongInfos>>();
		else {

			try {
				ObjectInputStream inputStream = new ObjectInputStream(
						new FileInputStream(library));
				songLibrary = (SongLibraryMap<String, List<SongInfos>>) inputStream
						.readObject();

				inputStream.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private void saveSongLibrary() {

		if (songLibrary != null) {

			try {
				ObjectOutputStream outputStream = new ObjectOutputStream(
						new FileOutputStream(new File("E:/Hub/SongLibrary.dat")));
				outputStream.writeObject(songLibrary);
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



}
