﻿       
namespace eSSP_example
{
    // This class contains a list of definitions of bytes that can be sent or
    // receieved from the validator. 
    class CCommands
    {
        public const byte SSP_CMD_RESET = 0x01;
        public const byte SSP_CMD_HOST_PROTOCOL_VERSION = 0x06;
        public const byte SSP_CMD_SYNC = 0x11;
        public const byte SSP_CMD_SET_GENERATOR = 0x4A;
        public const byte SSP_CMD_SET_MODULUS = 0x4B;
        public const byte SSP_CMD_KEY_EXCHANGE = 0x4C;
        public const byte SSP_CMD_SET_INHIBITS = 0x02;
        public const byte SSP_CMD_ENABLE = 0xA;
        public const byte SSP_CMD_DISABLE = 0x09;
        public const byte SSP_CMD_POLL = 0x7;
        public const byte SSP_CMD_SETUP_REQUEST = 0x05;
        public const byte SSP_CMD_DISPLAY_ON = 0x03;
        public const byte SSP_CMD_DISPLAY_OFF = 0x04;
        public const byte SSP_CMD_ENABLE_PAYOUT = 0x5C;
        public const byte SSP_CMD_DISABLE_PAYOUT = 0x5B;
        public const byte SSP_CMD_SET_ROUTING = 0x3B;
        public const byte SSP_CMD_GET_ROUTING = 0x3C;
        public const byte SSP_CMD_PAYOUT_AMOUNT = 0x33;
        public const byte SSP_CMD_GET_NOTE_AMOUNT = 0x35;
        public const byte SSP_CMD_FLOAT_AMOUNT = 0x3D;
        public const byte SSP_CMD_EMPTY = 0x3F;
        public const byte SSP_CMD_SMART_EMPTY = 0x52;
        public const byte SSP_CMD_CASHBOX_PAYOUT_OP_DATA = 0x53;
        public const byte SSP_CMD_GET_NOTE_COUNTERS = 0x58;
        public const byte SSP_CMD_RESET_NOTE_COUNTERS = 0x59;
        public const byte SSP_CMD_LAST_REJECT_CODE = 0x17;
        public const byte SSP_CMD_PAYOUT_BY_DENOMINATION = 0x46;

        public const byte SSP_POLL_RESET = 0xF1;
        public const byte SSP_POLL_DISABLED = 0xE8;
        public const byte SSP_POLL_NOTE_READ = 0xEF;
        public const byte SSP_POLL_CREDIT = 0xEE;
        public const byte SSP_POLL_REJECTING = 0xED;
        public const byte SSP_POLL_REJECTED = 0xEC;
        public const byte SSP_POLL_STACKING = 0xCC;
        public const byte SSP_POLL_STACKED = 0xEB;
        public const byte SSP_POLL_EMPTYING = 0xC2;
        public const byte SSP_POLL_EMPTIED = 0xC3;
        public const byte SSP_POLL_SAFE_JAM = 0xEA;
        public const byte SSP_POLL_UNSAFE_JAM = 0xE9;
        public const byte SSP_POLL_FRAUD_ATTEMPT = 0xE6;
        public const byte SSP_POLL_STACKER_FULL = 0xE7;
        public const byte SSP_POLL_NOTE_CLEARED_FROM_FRONT = 0xE1;
        public const byte SSP_POLL_NOTE_CLEARED_TO_CASHBOX = 0xE2;
        public const byte SSP_POLL_CASHBOX_REMOVED = 0xE3;
        public const byte SSP_POLL_CASHBOX_REPLACED = 0xE4;
        public const byte SSP_POLL_NOTE_STORED = 0xDB;
        public const byte SSP_POLL_NOTE_DISPENSING = 0xDA;
        public const byte SSP_POLL_NOTE_DISPENSED = 0xD2;
        public const byte SSP_POLL_NOTE_TRANSFERRED_TO_STACKER = 0xC9;
        public const byte SSP_POLL_FLOATING = 0xD7;
        public const byte SSP_POLL_FLOATED = 0xD8;
        public const byte SSP_POLL_SMART_EMPTYING = 0xB3;
        public const byte SSP_POLL_SMART_EMPTIED = 0xB4;
        public const byte SSP_POLL_PAYOUT_ERROR = 0xB1;
        public const byte SSP_POLL_JAMMED = 0xD5;
        public const byte SSP_POLL_HALTED = 0xD6;
        public const byte SSP_POLL_INCOMPLETE_PAYOUT = 0xDD;
        public const byte SSP_POLL_INCOMPLETE_FLOAT = 0xDC;
        public const byte SSP_POLL_NOTE_HELD_IN_BEZEL = 0xCE;
        public const byte SSP_POLL_NOTE_PAID_INTO_STORE_ON_POWERUP = 0xCB;
        public const byte SSP_POLL_NOTE_PAID_INTO_STACKER_ON_POWERUP = 0xCA;
        public const byte SSP_POLL_PAYOUT_OUT_OF_SERVICE = 0xC6;
        public const byte SSP_POLL_TIMEOUT = 0xD9;

        public const byte SSP_RESPONSE_CMD_OK = 0xF0;
        public const byte SSP_RESPONSE_CMD_UNKNOWN = 0xF2;
        public const byte SSP_RESPONSE_CMD_WRONG_PARAMS = 0xF3;
        public const byte SSP_RESPONSE_CMD_PARAM_OUT_OF_RANGE = 0xF4;
        public const byte SSP_RESPONSE_CMD_CANNOT_PROCESS = 0xF5;
        public const byte SSP_RESPONSE_CMD_SOFTWARE_ERROR = 0xF6;
        public const byte SSP_RESPONSE_CMD_FAIL = 0xF8;
        public const byte SSP_RESPONSE_CMD_KEY_NOT_SET = 0xFA;
    }
}