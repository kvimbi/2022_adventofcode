package main

import "testing"

func TestString1(t *testing.T) {
	result := FindStartPacket("bvwbjplbgvbhsrlpgdmjqwftvncz")
	if result != 5 {
		t.Fail()
	}
}

func TestString2(t *testing.T) {
	result := FindStartPacket("nppdvjthqldpwncqszvftbrmjlhg")
	if result != 6 {
		t.Fail()
	}
}

func TestString3(t *testing.T) {
	result := FindStartPacket("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
	if result != 10 {
		t.Fail()
	}
}
