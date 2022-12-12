package com.springaop.algorithm.DesignPattern.AdapterPattern;

public class AdapterPatternDemo {
	public static void main(String[] args) {
	/*
    Convert the interface of a class into another interface clients expect.
    Adapter lets classes work together that couldn’t otherwise because of
    incompatible interfaces.
	*/
		AudioPlayer audioPlayer = new AudioPlayer();

		audioPlayer.play("mp3", "beyond the horizon.mp3");
		audioPlayer.play("mp4", "alone.mp4");
		audioPlayer.play("vlc", "far far away.vlc");
		audioPlayer.play("avi", "mind me.avi");
	}
}
