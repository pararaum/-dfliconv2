package dfliconv2.format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dfliconv2.Value;
import dfliconv2.value.Const;

public class P4FliViewer {

	public static final char[] PRG = {
	    0x01, 0x10, 0x0B, 0x10, 0x0A, 0x00, 0x9E, 0x34, 0x31, 0x30, 0x39, 0x00,
	    0x00, 0x00, 0x78, 0xD8, 0xA2, 0xFF, 0x9A, 0xA9, 0xCA, 0x8D, 0xFC, 0xFF,
	    0xA9, 0x10, 0x8D, 0xFD, 0xFF, 0x20, 0x70, 0x12, 0x20, 0x93, 0x10, 0xAE,
	    0xEC, 0x17, 0xCA, 0xEC, 0x1D, 0xFF, 0xF0, 0xFB, 0xE8, 0xEC, 0x1D, 0xFF,
	    0xF0, 0xFB, 0x8E, 0x0B, 0xFF, 0xAD, 0xEE, 0x17, 0x8D, 0x0A, 0xFF, 0xAD,
	    0xF0, 0x17, 0x8D, 0xFE, 0xFF, 0xAD, 0xF2, 0x17, 0x8D, 0xFF, 0xFF, 0xCE,
	    0x09, 0xFF, 0x58, 0xA2, 0x00, 0x20, 0xE1, 0x10, 0xAD, 0xFC, 0x17, 0xD0,
	    0x24, 0xAD, 0xFD, 0x17, 0xF0, 0x28, 0x10, 0x1D, 0xA9, 0x7F, 0x8D, 0x30,
	    0xFD, 0x8D, 0x08, 0xFF, 0xAD, 0x08, 0xFF, 0x29, 0x10, 0xD0, 0x0F, 0x8A,
	    0xF0, 0x0C, 0xAD, 0xFB, 0x1F, 0xF0, 0x0F, 0xA5, 0xD9, 0x4A, 0x8D, 0xFD,
	    0x17, 0x8A, 0x48, 0x20, 0x96, 0x10, 0x68, 0xAA, 0x10, 0xCB, 0x78, 0xA9,
	    0x0B, 0x8D, 0x06, 0xFF, 0x8D, 0x3E, 0xFF, 0x8D, 0xD0, 0xFD, 0x6C, 0xFA,
	    0x17, 0x6C, 0xF4, 0x17, 0x6C, 0xF6, 0x17, 0xAE, 0xFC, 0x17, 0xF0, 0x14,
	    0xCA, 0x8E, 0xFC, 0x17, 0x20, 0x3F, 0x11, 0xA5, 0xD9, 0x18, 0xED, 0xFC,
	    0x17, 0xAA, 0x20, 0x3F, 0x11, 0x6C, 0xF8, 0x17, 0xAE, 0xFD, 0x17, 0x30,
	    0x13, 0xF0, 0x11, 0xCA, 0x8E, 0xFD, 0x17, 0x20, 0xEF, 0x10, 0xA5, 0xD9,
	    0x18, 0xED, 0xFD, 0x17, 0xAA, 0x20, 0xEF, 0x10, 0x6C, 0xF8, 0x17, 0x78,
	    0x8D, 0x3E, 0xFF, 0x8D, 0xD0, 0xFD, 0x6C, 0xFC, 0xFF, 0x2C, 0x07, 0xFF,
	    0x70, 0x03, 0xA9, 0xFA, 0x2C, 0xA9, 0xE1, 0x4C, 0xE4, 0x10, 0xAD, 0xF2,
	    0x15, 0xCD, 0x1D, 0xFF, 0xD0, 0xFB, 0xCD, 0x1D, 0xFF, 0xF0, 0xFB, 0x60,
	    0x86, 0xE0, 0x20, 0xF1, 0x11, 0x24, 0xDB, 0x30, 0x2F, 0xA9, 0x8E, 0xAA,
	    0xA0, 0x00, 0x20, 0xF0, 0x16, 0x20, 0x03, 0x12, 0x20, 0x43, 0x12, 0xA9,
	    0x0A, 0x20, 0x62, 0x12, 0x20, 0x03, 0x12, 0xA5, 0xE0, 0x29, 0x03, 0xAA,
	    0xBD, 0xD8, 0x17, 0x2C, 0xFD, 0x1F, 0x70, 0x03, 0xA8, 0xD0, 0x03, 0xBC,
	    0xE0, 0x17, 0xAA, 0xA9, 0x14, 0x4C, 0x4C, 0x12, 0xA9, 0x14, 0xA2, 0x08,
	    0xA0, 0x08, 0x20, 0x4C, 0x12, 0x20, 0x43, 0x12, 0x20, 0x60, 0x12, 0x20,
	    0x03, 0x12, 0x20, 0x60, 0x12, 0x4C, 0x43, 0x12, 0x86, 0xE0, 0x20, 0xF1,
	    0x11, 0x24, 0xDB, 0x30, 0x17, 0xA5, 0xE0, 0x29, 0x03, 0xD0, 0x03, 0xA9,
	    0x8D, 0x2C, 0xA9, 0xEE, 0xAA, 0xA0, 0x00, 0x20, 0xF0, 0x16, 0x20, 0x03,
	    0x12, 0x4C, 0x7E, 0x11, 0xA5, 0xE0, 0xC9, 0x64, 0x29, 0x03, 0x90, 0x02,
	    0x09, 0x04, 0xAA, 0xBD, 0xD8, 0x17, 0x2C, 0xFD, 0x1F, 0x70, 0x03, 0xA8,
	    0xD0, 0x03, 0xBC, 0xE0, 0x17, 0xAA, 0xA9, 0x14, 0x20, 0x4C, 0x12, 0x06,
	    0xE0, 0xA6, 0xE0, 0xBC, 0x00, 0x1D, 0xBD, 0x00, 0x19, 0xAA, 0xA9, 0x07,
	    0x20, 0x4C, 0x12, 0x20, 0x60, 0x12, 0x24, 0xDB, 0x30, 0x14, 0x20, 0x60,
	    0x12, 0x20, 0x03, 0x12, 0xA6, 0xE0, 0xBC, 0x01, 0x1D, 0xBD, 0x01, 0x19,
	    0xAA, 0xA9, 0x07, 0x4C, 0x4C, 0x12, 0x20, 0x03, 0x12, 0x20, 0x60, 0x12,
	    0xA6, 0xE0, 0xBD, 0x01, 0x19, 0xDD, 0x00, 0x19, 0xD0, 0x09, 0xBD, 0x01,
	    0x1A, 0x85, 0xE3, 0xA9, 0x15, 0xD0, 0x04, 0x85, 0xE3, 0xA9, 0x07, 0x85,
	    0xE2, 0xBD, 0x01, 0x1D, 0xDD, 0x00, 0x1D, 0xD0, 0x09, 0xBD, 0x01, 0x1E,
	    0x85, 0xE8, 0xA9, 0x15, 0xD0, 0x04, 0x85, 0xE8, 0xA9, 0x07, 0x85, 0xE4,
	    0xA0, 0x01, 0xA5, 0xE3, 0xA6, 0xE8, 0x20, 0xF0, 0x16, 0xA0, 0x03, 0xA5,
	    0xE2, 0xA6, 0xE4, 0x4C, 0xF0, 0x16, 0xBD, 0x40, 0x20, 0x85, 0xDC, 0x85,
	    0xDE, 0xBD, 0x40, 0x60, 0x85, 0xDD, 0x18, 0x65, 0xE5, 0x85, 0xDF, 0x60,
	    0xA0, 0x00, 0xB1, 0xDC, 0xC9, 0xA0, 0xD0, 0x01, 0x60, 0xC9, 0xEA, 0xF0,
	    0x25, 0xC9, 0x24, 0xF0, 0x24, 0xC9, 0xA2, 0xF0, 0x20, 0xC9, 0xA9, 0xF0,
	    0x1C, 0xC9, 0x4C, 0xD0, 0x1B, 0xC8, 0xB1, 0xDC, 0x48, 0xC8, 0xB1, 0xDC,
	    0x85, 0xDD, 0x18, 0x65, 0xE5, 0x85, 0xDF, 0x68, 0x85, 0xDC, 0x85, 0xDE,
	    0x90, 0xCE, 0xA9, 0x01, 0x2C, 0xA9, 0x02, 0x2C, 0xA9, 0x03, 0x20, 0x62,
	    0x12, 0x4C, 0x05, 0x12, 0xAD, 0x00, 0x19, 0x29, 0x40, 0xAA, 0xA8, 0xA9,
	    0x07, 0x85, 0xE2, 0x84, 0xE3, 0x8A, 0xA6, 0xE3, 0xA0, 0x01, 0x20, 0xF0,
	    0x16, 0xA5, 0xE2, 0xAA, 0xA0, 0x03, 0x20, 0xF0, 0x16, 0xA9, 0x05, 0x18,
	    0x65, 0xDC, 0x85, 0xDC, 0x85, 0xDE, 0x90, 0x04, 0xE6, 0xDD, 0xE6, 0xDF,
	    0x60, 0x78, 0x8D, 0x3E, 0xFF, 0x8D, 0xD0, 0xFD, 0xAD, 0x07, 0xFF, 0x29,
	    0xDF, 0x8D, 0x07, 0xFF, 0x20, 0xD4, 0x10, 0x20, 0x84, 0xFF, 0xA9, 0x0B,
	    0x8D, 0x06, 0xFF, 0x8D, 0x3F, 0xFF, 0xAD, 0xFE, 0x17, 0x0D, 0xFF, 0x17,
	    0xF0, 0x0B, 0x20, 0x90, 0x10, 0xA9, 0x00, 0x8D, 0xFE, 0x17, 0x8D, 0xFF,
	    0x17, 0xAD, 0xFC, 0x1F, 0x8D, 0x19, 0xFF, 0xAE, 0xFE, 0x1F, 0xE0, 0x01,
	    0xA9, 0x00, 0x6A, 0x85, 0xDA, 0xAD, 0xFF, 0x1F, 0xE0, 0x01, 0x90, 0x01,
	    0x6A, 0x4A, 0x29, 0x7E, 0xC9, 0x40, 0xB0, 0x02, 0xA9, 0x40, 0x85, 0xD9,
	    0x2C, 0x07, 0xFF, 0x70, 0x03, 0xA9, 0x7C, 0x2C, 0xA9, 0x74, 0xC5, 0xD9,
	    0x90, 0x02, 0xA5, 0xD9, 0x85, 0xD9, 0x0A, 0x85, 0xD8, 0xAD, 0xFB, 0x1F,
	    0xF0, 0x3E, 0xAD, 0xFC, 0x1F, 0x29, 0x70, 0x85, 0xE0, 0x4A, 0x4A, 0x4A,
	    0x4A, 0x05, 0xE0, 0xA2, 0x00, 0x9D, 0x00, 0x08, 0x9D, 0x00, 0x09, 0x9D,
	    0x00, 0x0A, 0x9D, 0x00, 0x0B, 0xE8, 0xD0, 0xF1, 0xAD, 0xFC, 0x1F, 0x29,
	    0x0F, 0x85, 0xE0, 0x0A, 0x0A, 0x0A, 0x0A, 0x05, 0xE0, 0x9D, 0x00, 0x0C,
	    0x9D, 0x00, 0x0D, 0x9D, 0x00, 0x0E, 0x9D, 0x00, 0x0F, 0xE8, 0xD0, 0xF1,
	    0xA5, 0xD9, 0x4A, 0x2C, 0xA9, 0x00, 0x8D, 0xFC, 0x17, 0xA9, 0x80, 0x8D,
	    0xFD, 0x17, 0xAD, 0xFD, 0x1F, 0x29, 0xC0, 0x8D, 0xFD, 0x1F, 0xAD, 0x07,
	    0xFF, 0x29, 0x40, 0x85, 0xE0, 0xA6, 0xD8, 0xA9, 0x00, 0x1D, 0xFF, 0x18,
	    0xCA, 0xD0, 0xFA, 0xEC, 0xFD, 0x1F, 0xF0, 0x08, 0xA6, 0xD8, 0x1D, 0xFF,
	    0x1C, 0xCA, 0xD0, 0xFA, 0x29, 0x07, 0xD0, 0x06, 0xA5, 0xE0, 0x09, 0x08,
	    0x85, 0xE0, 0xBD, 0x00, 0x19, 0x29, 0x17, 0x05, 0xE0, 0x9D, 0x00, 0x19,
	    0xBD, 0x00, 0x1D, 0x29, 0x17, 0x05, 0xE0, 0x9D, 0x00, 0x1D, 0xE8, 0xE0,
	    0xF8, 0xD0, 0xE7, 0xA5, 0xD8, 0xC9, 0xC9, 0xA9, 0x00, 0xAA, 0x86, 0xDC,
	    0x86, 0xDE, 0x6A, 0x85, 0xDB, 0x0A, 0xAD, 0xFD, 0x1F, 0x2A, 0x2A, 0x2A,
	    0xA8, 0xB9, 0xC8, 0x17, 0x85, 0xE6, 0x85, 0xDD, 0xB9, 0xD0, 0x17, 0x85,
	    0xE7, 0x85, 0xDF, 0x38, 0xF9, 0xC8, 0x17, 0x85, 0xE5, 0xB9, 0xC0, 0x17,
	    0x85, 0xE1, 0xC0, 0x04, 0xB0, 0x78, 0xA9, 0x08, 0x20, 0xB6, 0x16, 0x86,
	    0xE0, 0xA0, 0xFF, 0xA9, 0x40, 0x2C, 0xFD, 0x1F, 0x70, 0x03, 0xA2, 0x40,
	    0x2C, 0xA2, 0x80, 0x20, 0xF0, 0x16, 0xA6, 0xE0, 0x8A, 0x29, 0x03, 0xD0,
	    0x03, 0xA9, 0x09, 0x2C, 0xA9, 0x0A, 0x20, 0xAA, 0x16, 0xA9, 0x0D, 0x20,
	    0xB6, 0x16, 0xA0, 0xF7, 0x38, 0x20, 0x23, 0x15, 0xA0, 0xFC, 0x38, 0x20,
	    0x37, 0x15, 0xE8, 0xE4, 0xD9, 0xA9, 0x0B, 0x69, 0x00, 0x20, 0xB6, 0x16,
	    0xA9, 0x0D, 0x20, 0xB6, 0x16, 0xA0, 0xF7, 0x18, 0x20, 0x23, 0x15, 0xA0,
	    0xFC, 0x18, 0x20, 0x37, 0x15, 0xE4, 0xD9, 0x90, 0xC3, 0xB0, 0x6E, 0xA9,
	    0x05, 0x2C, 0xA9, 0x01, 0x20, 0xB6, 0x16, 0xA9, 0x07, 0x20, 0xB6, 0x16,
	    0xCA, 0xA0, 0xEF, 0x38, 0x20, 0x37, 0x15, 0xE8, 0xA0, 0xFC, 0x18, 0x20,
	    0x37, 0x15, 0xE4, 0xD9, 0xB0, 0x4F, 0xA9, 0x00, 0x20, 0xAA, 0x16, 0xA0,
	    0xFC, 0x38, 0x20, 0x23, 0x15, 0xE8, 0xC6, 0xE1, 0xF0, 0x0C, 0xE4, 0xD9,
	    0xB0, 0xCD, 0xE0, 0x65, 0xD0, 0xCC, 0xA9, 0x04, 0xD0, 0xCA, 0xA9, 0x15,
	    0x85, 0xE1, 0xE4, 0xD9, 0xB0, 0x03, 0xA9, 0x02, 0x2C, 0xA9, 0x06, 0x20,
	    0xB6, 0x16, 0x86, 0xE0, 0xA5, 0xDF, 0x18, 0x69, 0x02, 0x48, 0xAA, 0x38,
	    0xE5, 0xE5, 0x48, 0xA0, 0xFF, 0x20, 0xF0, 0x16, 0xA6, 0xE0, 0xA9, 0x00,
	    0x85, 0xDC, 0x85, 0xDE, 0x68, 0x85, 0xDD, 0x68, 0x85, 0xDF, 0x4C, 0x02,
	    0x14, 0xA0, 0x00, 0xA9, 0x60, 0xAA, 0x20, 0xF0, 0x16, 0xA9, 0xFF, 0x2C,
	    0x07, 0xFF, 0x70, 0x06, 0xA2, 0x36, 0xA0, 0xF9, 0xD0, 0x04, 0xA2, 0x04,
	    0xA0, 0xE0, 0x8D, 0x62, 0x15, 0x8E, 0x5D, 0x15, 0x29, 0xA3, 0x48, 0xA5,
	    0xD9, 0xC9, 0x75, 0xB0, 0x20, 0xE9, 0x63, 0x48, 0x18, 0x69, 0xCE, 0x8D,
	    0xF2, 0x15, 0x68, 0x30, 0x0C, 0x49, 0xFF, 0x38, 0x6D, 0x5D, 0x15, 0xAA,
	    0x68, 0xE9, 0x00, 0xD0, 0x0F, 0x49, 0xFF, 0x38, 0xE9, 0x01, 0x18, 0x90,
	    0xF2, 0xE9, 0x66, 0x48, 0x69, 0xD1, 0x90, 0xDF, 0x8D, 0xEE, 0x17, 0x8E,
	    0xEC, 0x17, 0x24, 0xDA, 0x30, 0x13, 0x8D, 0xEF, 0x17, 0x8E, 0xED, 0x17,
	    0xA9, 0x15, 0x8D, 0xF3, 0x17, 0xA9, 0x46, 0x8D, 0xF1, 0x17, 0x4C, 0xE6,
	    0x14, 0xA9, 0xA2, 0x8D, 0xEF, 0x17, 0x8C, 0xED, 0x17, 0xA9, 0x16, 0x8D,
	    0xF3, 0x17, 0xA9, 0x05, 0x8D, 0xF1, 0x17, 0xA2, 0x00, 0x8E, 0xB0, 0x15,
	    0xAD, 0xFD, 0x1F, 0xC9, 0x40, 0x8A, 0x2A, 0x8D, 0xB8, 0x15, 0x2C, 0xFD,
	    0x1F, 0x30, 0x06, 0xA9, 0xC8, 0xA2, 0xE8, 0xD0, 0x04, 0xA9, 0xD8, 0xA2,
	    0xF0, 0x8D, 0xE9, 0x17, 0x8E, 0xEB, 0x17, 0xA6, 0xD9, 0xCA, 0x8A, 0x48,
	    0xAD, 0xFB, 0x1F, 0xD0, 0x06, 0x20, 0x3F, 0x11, 0x4C, 0x1D, 0x15, 0x20,
	    0xEF, 0x10, 0x68, 0xAA, 0xCA, 0x10, 0xEB, 0x60, 0x86, 0xE0, 0x8A, 0x2A,
	    0xAA, 0xBD, 0xFF, 0x19, 0x48, 0xBD, 0xFF, 0x1D, 0xAA, 0x68, 0x20, 0xF0,
	    0x16, 0xA6, 0xE0, 0x60, 0x86, 0xE0, 0x8A, 0x2A, 0xAA, 0xBD, 0xFF, 0x1A,
	    0x48, 0xBD, 0xFF, 0x1E, 0x4C, 0x2F, 0x15, 0x8D, 0x03, 0x16, 0xAD, 0x1E,
	    0xFF, 0x29, 0x0E, 0x4A, 0x8D, 0x53, 0x15, 0x10, 0x00, 0xA9, 0xA9, 0xA9,
	    0xA9, 0xA9, 0xA9, 0xA5, 0xEA, 0xA9, 0x36, 0x8D, 0x1D, 0xFF, 0xA9, 0xFF,
	    0x8D, 0x1C, 0xFF, 0x8A, 0x48, 0x98, 0x48, 0xA2, 0x0D, 0xA9, 0x3B, 0xCA,
	    0xD0, 0xFB, 0x8D, 0x06, 0xFF, 0xA2, 0x17, 0xCA, 0xD0, 0xFD, 0xEA, 0xAD,
	    0x1F, 0xFF, 0xA0, 0x18, 0xA2, 0x00, 0x8C, 0x1B, 0xFF, 0x8E, 0x1A, 0xFF,
	    0x29, 0xFE, 0x8D, 0x1F, 0xFF, 0x09, 0x07, 0xEA, 0xEA, 0xA2, 0x7F, 0xA0,
	    0xD1, 0x8E, 0x1E, 0xFF, 0xEA, 0xEA, 0xEA, 0xEA, 0x8C, 0x1E, 0xFF, 0xA2,
	    0x03, 0xA0, 0x00, 0xCA, 0xD0, 0xFB, 0x8D, 0x1F, 0xFF, 0xA0, 0x51, 0x8C,
	    0x1E, 0xFF, 0xA2, 0x05, 0xA9, 0x00, 0xCA, 0xD0, 0xFB, 0xAA, 0x49, 0x01,
	    0x29, 0x01, 0x8D, 0xB0, 0x15, 0xB5, 0xE6, 0x8D, 0xCE, 0x15, 0xBD, 0xE8,
	    0x17, 0x8D, 0x12, 0xFF, 0xBD, 0xEA, 0x17, 0xA2, 0x0A, 0x20, 0x00, 0xA0,
	    0xAE, 0xB0, 0x15, 0xBD, 0xEC, 0x17, 0x8D, 0x0B, 0xFF, 0xBD, 0xEE, 0x17,
	    0x8D, 0x0A, 0xFF, 0xBD, 0xF0, 0x17, 0x8D, 0xFE, 0xFF, 0xBD, 0xF2, 0x17,
	    0x8D, 0xFF, 0xFF, 0xA9, 0xCE, 0xCD, 0x1D, 0xFF, 0xD0, 0xFB, 0xA9, 0xCE,
	    0x8D, 0x1D, 0xFF, 0xA9, 0x0B, 0x8D, 0x06, 0xFF, 0xCE, 0x09, 0xFF, 0x68,
	    0xA8, 0x68, 0xAA, 0xA9, 0x00, 0x40, 0x8D, 0x45, 0x16, 0xAD, 0x1E, 0xFF,
	    0x29, 0x0E, 0x4A, 0x8D, 0x12, 0x16, 0x10, 0x00, 0xA9, 0xA9, 0xA9, 0xA9,
	    0xA9, 0xA9, 0xA5, 0xEA, 0x8A, 0x48, 0xA2, 0x0D, 0xA9, 0x6D, 0xCA, 0xD0,
	    0xFB, 0x18, 0x8D, 0x1E, 0xFF, 0xAD, 0x0B, 0xFF, 0x69, 0x06, 0x8D, 0x0B,
	    0xFF, 0x90, 0x03, 0xEE, 0x0A, 0xFF, 0xA9, 0x47, 0x8D, 0xFE, 0xFF, 0xA9,
	    0x16, 0x8D, 0xFF, 0xFF, 0xCE, 0x09, 0xFF, 0x68, 0xAA, 0xA9, 0x00, 0x40,
	    0x8D, 0x87, 0x16, 0xAD, 0x1E, 0xFF, 0x29, 0x0E, 0x4A, 0x8D, 0x54, 0x16,
	    0x10, 0x00, 0xA9, 0xA9, 0xA9, 0xA9, 0xA9, 0xA9, 0xA5, 0xEA, 0x8A, 0x48,
	    0xA9, 0x89, 0x8D, 0xFE, 0xFF, 0xA9, 0x16, 0x8D, 0xFF, 0xFF, 0xA2, 0x07,
	    0xA9, 0xAD, 0xCA, 0xD0, 0xFB, 0x18, 0x8D, 0x1E, 0xFF, 0xAD, 0x0B, 0xFF,
	    0x69, 0x06, 0x8D, 0x0B, 0xFF, 0x90, 0x03, 0xEE, 0x0A, 0xFF, 0xCE, 0x09,
	    0xFF, 0x68, 0xAA, 0xA9, 0x00, 0x40, 0x48, 0xCE, 0x1D, 0xFF, 0xAD, 0xEC,
	    0x17, 0x8D, 0x0B, 0xFF, 0xAD, 0xEE, 0x17, 0x8D, 0x0A, 0xFF, 0xAD, 0xF0,
	    0x17, 0x8D, 0xFE, 0xFF, 0xAD, 0xF2, 0x17, 0x8D, 0xFF, 0xFF, 0xCE, 0x09,
	    0xFF, 0x68, 0x40, 0x48, 0xA5, 0xDC, 0x9D, 0x40, 0x20, 0xA5, 0xDD, 0x9D,
	    0x40, 0x60, 0x68, 0x86, 0xE0, 0xAA, 0xBC, 0xB6, 0x17, 0x88, 0x98, 0x48,
	    0x18, 0x7D, 0xA8, 0x17, 0xAA, 0xAD, 0xFD, 0x1F, 0xD0, 0x0B, 0xBD, 0x0A,
	    0x17, 0x91, 0xDC, 0xCA, 0x88, 0x10, 0xF7, 0x30, 0x0B, 0xBD, 0x0A, 0x17,
	    0x91, 0xDC, 0x91, 0xDE, 0xCA, 0x88, 0x10, 0xF5, 0x68, 0x38, 0x65, 0xDC,
	    0x85, 0xDC, 0x85, 0xDE, 0x90, 0x04, 0xE6, 0xDD, 0xE6, 0xDF, 0xA6, 0xE0,
	    0x60, 0xC0, 0x80, 0x90, 0x04, 0xC6, 0xDD, 0xC6, 0xDF, 0x91, 0xDC, 0xAD,
	    0xFD, 0x1F, 0xF0, 0x03, 0x8A, 0x91, 0xDE, 0x98, 0x10, 0x04, 0xE6, 0xDD,
	    0xE6, 0xDF, 0x60, 0xA0, 0x00, 0x8C, 0x14, 0xFF, 0xA0, 0x00, 0x8C, 0x07,
	    0xFF, 0xA0, 0x00, 0x8C, 0x15, 0xFF, 0xEA, 0x24, 0xEA, 0xEA, 0x4C, 0x00,
	    0x00, 0xA9, 0x00, 0x24, 0xEA, 0x9D, 0x08, 0xFF, 0xA2, 0xCA, 0x24, 0xEA,
	    0xA2, 0xCA, 0x4C, 0x00, 0x00, 0xA0, 0x00, 0x8C, 0x16, 0xFF, 0xA0, 0x00,
	    0x8C, 0x07, 0xFF, 0x8E, 0x1D, 0xFF, 0xA0, 0x00, 0x8C, 0x16, 0xFF, 0x8D,
	    0x14, 0xFF, 0xEE, 0x14, 0xFF, 0xA2, 0xCA, 0x8E, 0x1D, 0xFF, 0xA0, 0x00,
	    0x8C, 0x07, 0xFF, 0xA0, 0x00, 0x8C, 0x15, 0xFF, 0xA0, 0x00, 0x8C, 0x16,
	    0xFF, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	    0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0F, 0x12, 0x16, 0x1A, 0x1D, 0x21,
	    0x26, 0x16, 0x38, 0x3B, 0x40, 0x3E, 0x43, 0x0F, 0x03, 0x04, 0x04, 0x03,
	    0x04, 0x05, 0x12, 0x02, 0x03, 0x03, 0x03, 0x05, 0x0F, 0xFF, 0x15, 0x15,
	    0x15, 0x61, 0x61, 0x80, 0xA0, 0x61, 0xA9, 0xA9, 0xA9, 0x70, 0x70, 0x8F,
	    0xAF, 0x73, 0xC9, 0xC9, 0xC9, 0x40, 0x48, 0x50, 0x58, 0x18, 0xA8, 0xB0,
	    0xB8, 0x80, 0x88, 0x90, 0x98, 0xE0, 0xC8, 0xD0, 0xD8, 0xC8, 0xD8, 0xE8,
	    0xF0, 0x36, 0xF9, 0xA3, 0xA2, 0x46, 0x05, 0x15, 0x16, 0xCA, 0x10, 0xEE,
	    0x10, 0xEE, 0x10, 0x0D, 0x10, 0x00, 0x00, 0x00, 0x00
	};
	
	private static int BLANK_FX        = 0x0ffc;
	private static int BORDER_COLOR    = 0x0ffd;
	private static int INTERLACE_FLAGS = 0x0ffe;
	private static int HEIGHT_HI_LO    = 0x0fff;
	
	
	private List<Value> prg;
	private int height;

	public P4FliViewer() 
	{
		prg = new ArrayList<>();
		for (int c : PRG)
			prg.add(new Const(c));
		prg.addAll(Collections.nCopies(0xD501-PRG.length, Const.ZERO));
		
		prg.set(BLANK_FX, Const.ONE);
	}
	
	public void setHeight(int h)
	{
		height = h;
		prg.set(HEIGHT_HI_LO+0, new Const(h/256));
		prg.set(HEIGHT_HI_LO+1, new Const(h%256));
	}
	
	public void setXShift(List<Value> xs)
	{
		for (int i = 0; i<xs.size(); i++)
			prg.set(0x901+i, xs.get(i));
	}
	
	public void setColor0(List<Value> c0)
	{
		for (int i = 0; i<c0.size(); i++)
			prg.set(0xA01+i, c0.get(i));
	}
	
	public void setColor3(List<Value> c3)
	{
		for (int i = 0; i<c3.size(); i++)
			prg.set(0xB01+i, c3.get(i));
	}
	
	public void setBitmap(List<Value> bm)
	{
		for (int i = 0; i<bm.size(); i++)
		{
			if (i<8000)
				prg.set(0x1001+24*8+i,bm.get(i));
			else
				prg.set(0x9001+i-8000,bm.get(i));
		}
	}
	
	public void setColors(List<Value> luma, List<Value> chroma, int y)
	{
		int s = luma.size();
		for (int i = 0; i<s; i++)
		{
			if (i<1000)
			{
				prg.set(0x3001+24+i+y*2048, luma.get(i));
				prg.set(0x3401+24+i+y*2048, chroma.get(i));
			}
			else
			{
				int j = i - 1000;
				if (y==0)
				{
					prg.set(0x0801+j, luma.get(i));
					prg.set(0x0c01+j, chroma.get(i));
				}
				else
				{
					prg.set(0x9001+j+y*2048, luma.get(i));
					prg.set(0x9401+j+y*2048, chroma.get(i));
				}
			}
		}
	}
	
	public List<Value> prg()
	{
		int end = prg.size();
		while (end>0 && prg.get(end-1)==Const.ZERO)
			end--;
		return prg.subList(0, end);
	}
}
