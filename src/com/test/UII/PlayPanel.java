package com.test.UII;

import javax.swing.JFrame;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JFrame;

import com.test.player.HigherPlayer;
import com.test.song.SongNode;
import com.test.ui.tool.IconButton;
import com.test.ui.tool.TimeProgressBar;

/**
 * 播放面板 使用NetBean Matisse构建.面板包含：3个标签，7个按钮，1个进度条，1个组合框和1个滑块条
 * 
 * @date 2014-10-9
 */

public class PlayPanel extends JPanel {
	
	// Variables declaration - do not modify
		private JLabel songName;
		private JButton download;
		private JButton mark;
		private JButton share;

		private TimeProgressBar timerProgressBar;
		private JLabel currentTimeCountLabel;
		private JLabel audioTotalTimeLabel;

		private JComboBox<String> mode;
		private JButton backPlay;
		private JButton play;
		private JButton frontPlay;
		private JSlider voiceAdjust;
		private JButton voiceControl;

		private JTree[] trees;

		private JFrame parentFrame;

		private HigherPlayer player;
		// 由FloatControl.Type.MASTER_GAIN得到的数据
		private final int minVoice = -80;
		private final int maxVoice = 6;
		private final int suitableVoice = -20;
	
	
	public PlayPanel()
	{
		initComponents();
		initPlayer();
	}
	
	private void initComponents()
	{
		songName = new JLabel("Music");
		download = new IconButton("下载", "icon/download.png");
		mark = new IconButton("我喜欢", "icon/heart.png");
		share = new IconButton("分享", "icon/share.png");

		timerProgressBar = new TimeProgressBar();
		mode = new JComboBox<>();
		
		backPlay = new IconButton("上一首", "icon/back.png");
		play = new IconButton("播放", "icon/play.png");
		play.setSelectedIcon(new ImageIcon("icon/pause.png"));
		play.setDisabledSelectedIcon(new ImageIcon("icon/play.png"));
		play.setMnemonic(KeyEvent.VK_ENTER);
		
		frontPlay = new IconButton("下一首", "icon/front.png");
		voiceControl = new IconButton("静音", "icon/voice.png");
		voiceAdjust = new JSlider(minVoice, maxVoice);
		voiceAdjust.setValue(suitableVoice);
		audioTotalTimeLabel = new JLabel("4:00");
		currentTimeCountLabel = new JLabel("0:00");
		
		setOpaque(false);
		setPreferredSize(new Dimension(360, 110));
		
		download.setMaximumSize(new Dimension(3030,30));
		download.setMinimumSize(new Dimension(30, 30));
		download.setPreferredSize(new Dimension(30, 30));
		
		mark.setPreferredSize(new Dimension(30, 30));
		
		share.setPreferredSize(new Dimension(30, 30));
		
		timerProgressBar.setPreferredSize(new Dimension(340, 7));
		
		mode.setModel(new DefaultComboBoxModel(new String[] { "单曲播放", "单曲循环",
				"顺序播放", "列表循环", "随机播放" }));
		
		backPlay.setPreferredSize(new Dimension(30, 30));
		
		play.setPreferredSize(new Dimension(30, 30));
		
		frontPlay.setPreferredSize(new Dimension(30, 30));

		voiceControl.setPreferredSize(new Dimension(30, 30));

		voiceAdjust.setPreferredSize(new Dimension(50, 20));

		audioTotalTimeLabel.setText("0:00");

		currentTimeCountLabel.setText("0:00");
		
		GroupLayout layout = new GroupLayout(this);
		
		this.setLayout(layout);
		
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(12, 12,
																		12)
																.addComponent(
																		songName,
																		GroupLayout.PREFERRED_SIZE,
																		230,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		download,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		mark,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		share,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(10, 10,
																		10)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createParallelGroup(
																								GroupLayout.Alignment.TRAILING,
																								false)
																								.addGroup(
																										layout.createSequentialGroup()
																												.addComponent(
																														currentTimeCountLabel)
																												.addPreferredGap(
																														LayoutStyle.ComponentPlacement.RELATED,
																														GroupLayout.DEFAULT_SIZE,
																														Short.MAX_VALUE)
																												.addComponent(
																														audioTotalTimeLabel))
																								.addComponent(
																										timerProgressBar,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										mode,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										LayoutStyle.ComponentPlacement.RELATED,
																										33,
																										Short.MAX_VALUE)
																								.addComponent(
																										backPlay,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										play,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										frontPlay,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE)
																								.addGap(28,
																										28,
																										28)
																								.addComponent(
																										voiceControl,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										voiceAdjust,
																										GroupLayout.PREFERRED_SIZE,
																										81,
																										GroupLayout.PREFERRED_SIZE)
																								.addGap(1,
																										1,
																										1)))))
								.addGap(0, 7, Short.MAX_VALUE)));

		layout.linkSize(SwingConstants.HORIZONTAL, new Component[] { download,
				mark, share });

		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												GroupLayout.Alignment.LEADING)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addGap(10,
																																		10,
																																		10)
																																.addGroup(
																																		layout.createParallelGroup(
																																				GroupLayout.Alignment.BASELINE)
																																				.addComponent(
																																						download,
																																						GroupLayout.PREFERRED_SIZE,
																																						GroupLayout.DEFAULT_SIZE,
																																						GroupLayout.PREFERRED_SIZE)
																																				.addComponent(
																																						mark,
																																						GroupLayout.PREFERRED_SIZE,
																																						20,
																																						GroupLayout.PREFERRED_SIZE)
																																				.addComponent(
																																						share,
																																						GroupLayout.PREFERRED_SIZE,
																																						20,
																																						GroupLayout.PREFERRED_SIZE))
																																.addGap(6,
																																		6,
																																		6))
																												.addGroup(
																														GroupLayout.Alignment.TRAILING,
																														layout.createSequentialGroup()
																																.addContainerGap()
																																.addComponent(
																																		songName,
																																		GroupLayout.PREFERRED_SIZE,
																																		20,
																																		GroupLayout.PREFERRED_SIZE)
																																.addPreferredGap(
																																		LayoutStyle.ComponentPlacement.UNRELATED)))
																								.addComponent(
																										timerProgressBar,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(55,
																										55,
																										55)
																								.addGroup(
																										layout.createParallelGroup(
																												GroupLayout.Alignment.CENTER)
																												.addComponent(
																														currentTimeCountLabel)
																												.addComponent(
																														audioTotalTimeLabel))))
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.CENTER)
																				.addComponent(
																						play,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						backPlay,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						frontPlay,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(76, 76,
																		76)
																.addComponent(
																		voiceControl,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														GroupLayout.Alignment.CENTER,
														layout.createSequentialGroup()
																.addGap(80, 80,
																		80)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addComponent(
																						voiceAdjust,
																						GroupLayout.Alignment.CENTER,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						mode,
																						GroupLayout.Alignment.CENTER,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		layout.linkSize(SwingConstants.VERTICAL, new Component[] { download,
				mark, share });
		
	}
	
	private void initPlayer()
	{
		player = new HigherPlayer();
		
		player.setPlayButton(play);
		player.setSongNameLabel(songName);
		player.setAudioTotalTimeLabel(audioTotalTimeLabel);
		player.setCurrentTimeCountLabel(currentTimeCountLabel);
		player.setTimerProgressBar(timerProgressBar);
		
		timerProgressBar.setTimerControl(player.IsPause);
		timerProgressBar.setCurrentTimeCountLabel(currentTimeCountLabel);
		
		setButtonsAction();
		setOtherAction();
		
	}
	
	public HigherPlayer getHigherPlayer() {
		return player;
	}
	
	public void setButtonsAction()
	{
		// play button
				play.addActionListener(event -> {

					if (player.getLoadSongName() == null) {
						return;
					}

					// 在第一次播放时 为了不进入下面一个if判断
					// 如果CurrentSong==null时，则进入，终止当前歌曲播放，可是当前播放线程还没有实例 报异常
					if (player.getPlayingSongName() == null) {
						player.setPlayingSongName(player.getLoadSongName());
					}

					// 在播放过程切换歌曲
					if (!player.getLoadSongName().equals(player.getPlayingSongName())
							&& !player.IsComplete) {

						// 终止当前歌曲播放
						player.end();
						player.setPlayingSongName(player.getLoadSongName());
					}

					// 是暂停 则播放
					if (player.IsPause) {

						if (player.NeedContinue) {
							// 继续播放
							player.resume();
							// 进度条的控制
							timerProgressBar.setTimerControl(false);
							timerProgressBar.resumeTimer();
							play.setIcon(play.getSelectedIcon());
							play.setToolTipText("暂停");
							return;
						}
						// 进度条的控制
						timerProgressBar.setTimerControl(false);
						// 播放歌曲
						player.open();
						// 声音控制
						voiceAdjust.setValue(suitableVoice);
						songName.setText(player.getPlayingSongName());
						play.setIcon(play.getSelectedIcon());
						play.setToolTipText("暂停");
						player.IsPause = false;

					} else {
						// 是播放 则暂停
						player.IsPause = true;

						timerProgressBar.setTimerControl(true);

						play.setIcon(play.getDisabledSelectedIcon());
						play.setToolTipText("播放");
					}

					songName.updateUI();
					play.updateUI();
					parentFrame.setVisible(true);
				});

				// backPlay button
				backPlay.addActionListener(event -> {

					if (player.getLoadSongName() == null) {
						return;
					}

					player.IsPlayNextSong = false;
					player.next();
					play.doClick();

				});

				// frontPlay button
				frontPlay.addActionListener(event -> {

					if (player.getLoadSongName() == null) {
						return;
					}

					player.IsPlayNextSong = true;
					player.next();
					play.doClick();

				});

				// voiceControl button
				voiceControl.addActionListener(event -> {

					if (!player.IsPause) {
						if (voiceAdjust.getValue() != minVoice) {
							player.setVoiceValue(minVoice);
							voiceAdjust.setValue(minVoice);
						} else {
							player.setVoiceValue(suitableVoice);
							voiceAdjust.setValue(suitableVoice);
						}
					}

				});

				// download button
				// 这里存在错误 当list获取焦点后 再添加歌曲 出错
				download.addActionListener(event -> {

					// 无载入歌时
					if (player.getLoadSongName() == null) {
						return;
					}

					if (!player.audio.toString().startsWith("http://")) {
						JOptionPane.showMessageDialog(null, "播放中歌曲是本地资源,无需下载 ", "",
								JOptionPane.PLAIN_MESSAGE);
						return;
					}

					SongNode playedSong = (SongNode) player.getPlayingSong();

					// 下载面板"下载中"列表添加播放中的歌曲
					addSongNodeToTreeList(trees[2], 0, playedSong);

					JOptionPane.showMessageDialog(null, "已成功添加到下载列表", null,
							JOptionPane.PLAIN_MESSAGE, null);

					download(playedSong);

				});

				// mark button
				// 这里存在错误 当list获取焦点后 再添加歌曲 出错
				mark.addActionListener(event -> {

					if (player.getLoadSongName() == null) {
						return;
					}

					SongNode playedSong = (SongNode) player.getPlayingSong();

					// 加入标记列表
					addSongNodeToTreeList(trees[1], 1, playedSong);

					JOptionPane.showMessageDialog(null, "已成功添加到收藏列表", null,
							JOptionPane.PLAIN_MESSAGE, null);
				});
	}
	
	
	public void setOtherAction() {
		timerProgressBar.addChangeListener(event -> {
		});

		voiceAdjust.addChangeListener(event -> {
			if (!player.IsPause) {
				player.setVoiceValue(voiceAdjust.getValue());
			}
		});

		mode.addActionListener(event -> {
			player.mode = mode.getSelectedIndex();

		});
	}
	
	public void download(SongNode songNode) {
		new Thread(
				() -> {

					try {

						// 打开资源链接
						HttpURLConnection httpURLConnection = (HttpURLConnection) player.audio
								.openConnection();

						// 开启IO流 读写数据
						BufferedInputStream inputStream = new BufferedInputStream(
								httpURLConnection.getInputStream());

						BufferedOutputStream outputStream = new BufferedOutputStream(
								new FileOutputStream(new File("E:/Hub/download/"
										+ player.getLoadSongName())));

						byte[] buff = new byte[1024];
						int onceRead = 0;
						while ((onceRead = inputStream.read(buff, 0,
								buff.length)) > 0) {
							outputStream.write(buff, 0, onceRead);
						}

						outputStream.flush();
						outputStream.close();
						inputStream.close();

						// 下载中列表移除节点
						removeSongNodeInTreeList(trees[2], 0, songNode);

						// 下载完成列表加入节点
						addSongNodeToTreeList(trees[2], 1, songNode);

						JOptionPane.showMessageDialog(null, "下载完成,文件存至E:/Hub/download",
								"", JOptionPane.PLAIN_MESSAGE);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}).start();
	}
	
	public void addSongNodeToTreeList(JTree tree, int index, SongNode songNode) {

		DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel()
				.getRoot();
		DefaultMutableTreeNode list = (DefaultMutableTreeNode) root
				.getChildAt(index);

		list.add(songNode);

		// 列表名更新
		String listName = (String) list.getUserObject();
		listName = listName.substring(0, listName.lastIndexOf("[")) + "["
				+ list.getChildCount() + "]";
		list.setUserObject(listName);

		// 如果这里不更新树的话 会不正确显示
		tree.updateUI();

	}
	
	public void removeSongNodeInTreeList(JTree tree, int index,
			SongNode songNode) {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel()
				.getRoot();
		DefaultMutableTreeNode list = (DefaultMutableTreeNode) root
				.getChildAt(index);

		list.remove(songNode);

		// 列表名更新
		String listName = (String) list.getUserObject();
		listName = listName.substring(0, listName.lastIndexOf("[")) + "["
				+ list.getChildCount() + "]";
		list.setUserObject(listName);

		// 如果这里不更新树的话 会不正确显示
		tree.updateUI();

	}
	
	public void setTrees(JTree[] trees) {
		this.trees = trees;
	}
	
	public void setLrcPanelTextArea(JTextArea textArea) {
		timerProgressBar.setLrcPanelTextArea(textArea);
	}
	
	public void setParentFrame(JFrame frame) {
		this.parentFrame = frame;
	}
	
	
	
	
	
}
