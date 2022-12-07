package main

import "testing"

func TestString1Marker(t *testing.T) {
	result := FindStartPacket("bvwbjplbgvbhsrlpgdmjqwftvncz", 4)
	if result != 5 {
		t.Fail()
	}
}

func TestString2(t *testing.T) {
	result := FindStartPacket("nppdvjthqldpwncqszvftbrmjlhg", 4)
	if result != 6 {
		t.Fail()
	}
}

func TestString3(t *testing.T) {
	result := FindStartPacket("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4)
	if result != 10 {
		t.Fail()
	}
}

func TestString1Message(t *testing.T) {
	result := FindStartPacket("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 14)
	if result != 19 {
		t.Fail()
	}
}

func TestString2Message(t *testing.T) {
	result := FindStartPacket("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14)
	if result != 29 {
		t.Fail()
	}
}
