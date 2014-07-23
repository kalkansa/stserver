package com.jadic.biz;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jadic.cmd.req.CmdGetMac2Req;
import com.jadic.cmd.req.CmdHeartbeatReq;
import com.jadic.cmd.req.CmdLoginReq;
import com.jadic.cmd.req.CmdModuleStatusReq;
import com.jadic.cmd.req.CmdTYRetReq;
import com.jadic.cmd.rsp.CmdGetMac2Rsp;
import com.jadic.cmd.rsp.CmdLoginRsp;
import com.jadic.cmd.rsp.CmdTYRetRsp;
import com.jadic.tcp.server.TcpChannel;
import com.jadic.utils.Const;
import com.jadic.utils.KKTool;
import com.jadic.ws.WSUtil;

/**
 * @author Jadic
 * @created 2014-4-14
 */
public class ThreadDisposeTcpChannelData implements Runnable {

    private final static Logger log = LoggerFactory.getLogger(ThreadDisposeTcpChannelData.class);
	private TcpChannel tcpChannel;

	final static int MAX_DISPOSE_COUNT = 20;// 线程处理每个通道一次最多连续处理次数

	public ThreadDisposeTcpChannelData(TcpChannel tcpChannel) {
		this.tcpChannel = tcpChannel;
	}

	@Override
	public void run() {
		boolean isSucc = tcpChannel.checkAndSetDisposeFlag();
		if (isSucc) {
			ChannelBuffer buffer = null;
			int disposeCount = 0;
			while ((buffer = tcpChannel.getNextBuffer()) != null) {
				disposeBuffer(buffer);
				disposeCount++;

				//if tcp channel is closed, dispose all buffer data in the queue
				if (!tcpChannel.isClosed()) {
					if (disposeCount >= MAX_DISPOSE_COUNT) {
					    log.warn("{} dispose buffer over {} times, left size:{}", tcpChannel, MAX_DISPOSE_COUNT, tcpChannel.getBufferQueueSize());
						break;
					}
				} 
			}
			if (!tcpChannel.isClosed()) {
				tcpChannel.setDisposing(false);
			} else {
				tcpChannel = null;
			}
			/*if (disposeCount > 1) {
				KKLog.info(tcpChannel + " dispose buffer " + disposeCount + " times");
			}*/
		}
	}

	private void disposeBuffer(ChannelBuffer buffer) {
		if (buffer == null || buffer.readableBytes() < Const.CMD_MIN_SIZE) {
			return;
		}

		short cmdFlag = buffer.getShort(buffer.readerIndex() + 1);
		switch (cmdFlag) {
		case Const.TER_TY_RET:
		    dealCmdTYRet(buffer);
		    break;
		case Const.TER_HEARTBEAT:
		    dealCmdHeartbeat(buffer);
		    break;
		case Const.TER_LOGIN:
		    dealCmdLogin(buffer);
		    break;
		case Const.TER_MODULE_STATUS:
		    dealCmdModuleStatus(buffer);
		    break;
		case Const.TER_GET_MAC2:
		    dealCmdGetMac2(buffer);
		    break;
		default:
			log.warn("Unknown command flag:{}", KKTool.byteArrayToHexStr(KKTool.short2BytesBigEndian(cmdFlag)));
			break;
		}
	}
	
	private void dealCmdTYRet(ChannelBuffer buffer) {
	    CmdTYRetReq cmdReq = new CmdTYRetReq();
	    if (cmdReq.disposeData(buffer)) {
	        log.info("recv tcp channel[{}] ty ret", tcpChannel);
	    }
	}

	private void dealCmdHeartbeat(ChannelBuffer buffer) {
	    CmdHeartbeatReq cmdReq = new CmdHeartbeatReq();
	    if (cmdReq.disposeData(buffer)) {
	        log.info("recv tcp channel[{}] heartbeat", tcpChannel);
	        CmdTYRetRsp cmdRsp = new CmdTYRetRsp();
	        cmdRsp.setCmdCommonField(cmdReq);
	        cmdRsp.setCmdFlagIdRsp(cmdReq.getCmdFlagId());
	        cmdRsp.setCmdSNoRsp(cmdReq.getCmdSNo());
	        this.sendData(cmdRsp.getSendBuffer());
	    }
	}
	
	private void dealCmdLogin(ChannelBuffer buffer) {
	    CmdLoginReq cmdReq = new CmdLoginReq();
	    if (cmdReq.disposeData(buffer)) {
	        log.info("tcp channel[{}] login", tcpChannel);
	        CmdLoginRsp cmdRsp = new CmdLoginRsp();
	        cmdRsp.setCmdCommonField(cmdReq);
	        cmdRsp.setCmdSNoRsp(cmdReq.getCmdSNo());
	    }
	}
	
	private void dealCmdModuleStatus(ChannelBuffer buffer) {
	    CmdModuleStatusReq cmdReq = new CmdModuleStatusReq();
	    if (cmdReq.disposeData(buffer)) {
	        log.info("recv tcp channel[{}] module status", tcpChannel);
	        CmdTYRetRsp cmdRsp = new CmdTYRetRsp();
	        cmdRsp.setCmdCommonField(cmdReq);
	        
	        this.sendData(cmdRsp.getSendBuffer());
	    }
	}
	
	private void dealCmdGetMac2(ChannelBuffer buffer) {
	    CmdGetMac2Req cmdReq = new CmdGetMac2Req();
	    if (cmdReq.disposeData(buffer)) {
	        log.info("recv get mac2[{}]", tcpChannel);
	        CmdGetMac2Rsp cmdRsp = new CmdGetMac2Rsp();
	        cmdRsp.setCmdCommonField(cmdReq);
	        String sMac2 = WSUtil.getWsUtil().getMac2(cmdReq);
	        byte[] mac2 = KKTool.strToHexBytes(sMac2, 4, 'F');
	        cmdRsp.setMac2(mac2);
	        sendData(cmdRsp.getSendBuffer());
	    }
	}
	
	private void sendData(ChannelBuffer buffer) {
	    if (this.tcpChannel != null && !this.tcpChannel.isClosed()) {
	        this.tcpChannel.sendData(KKTool.getEscapedBuffer(buffer));
	    }
	}
}
