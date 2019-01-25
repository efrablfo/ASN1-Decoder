/**
 * This class file was automatically generated by jASN1 v1.9.0 (http://www.openmuc.org)
 */

package chargingcdr;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.io.Serializable;
import org.openmuc.jasn1.ber.*;
import org.openmuc.jasn1.ber.types.*;
import org.openmuc.jasn1.ber.types.string.*;


public class FBCRatingRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	public static class ServiceClassInfo implements Serializable {

		private static final long serialVersionUID = 1L;

		public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
		public byte[] code = null;
		private List<chargingcdr.ServiceClassInfo> seqOf = null;

		public ServiceClassInfo() {
			seqOf = new ArrayList<chargingcdr.ServiceClassInfo>();
		}

		public ServiceClassInfo(byte[] code) {
			this.code = code;
		}

		public List<chargingcdr.ServiceClassInfo> getServiceClassInfo() {
			if (seqOf == null) {
				seqOf = new ArrayList<chargingcdr.ServiceClassInfo>();
			}
			return seqOf;
		}

		public int encode(OutputStream os) throws IOException {
			return encode(os, true);
		}

		public int encode(OutputStream os, boolean withTag) throws IOException {

			if (code != null) {
				for (int i = code.length - 1; i >= 0; i--) {
					os.write(code[i]);
				}
				if (withTag) {
					return tag.encode(os) + code.length;
				}
				return code.length;
			}

			int codeLength = 0;
			for (int i = (seqOf.size() - 1); i >= 0; i--) {
				codeLength += seqOf.get(i).encode(os, true);
			}

			codeLength += BerLength.encodeLength(os, codeLength);

			if (withTag) {
				codeLength += tag.encode(os);
			}

			return codeLength;
		}

		public int decode(InputStream is) throws IOException {
			return decode(is, true);
		}

		public int decode(InputStream is, boolean withTag) throws IOException {
			int codeLength = 0;
			int subCodeLength = 0;
			if (withTag) {
				codeLength += tag.decodeAndCheck(is);
			}

			BerLength length = new BerLength();
			codeLength += length.decode(is);
			int totalLength = length.val;

			while (subCodeLength < totalLength) {
				chargingcdr.ServiceClassInfo element = new chargingcdr.ServiceClassInfo();
				subCodeLength += element.decode(is, true);
				seqOf.add(element);
			}
			if (subCodeLength != totalLength) {
				throw new IOException("Decoded SequenceOf or SetOf has wrong length. Expected " + totalLength + " but has " + subCodeLength);

			}
			codeLength += subCodeLength;

			return codeLength;
		}

		public void encodeAndSave(int encodingSizeGuess) throws IOException {
			ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
			encode(os, false);
			code = os.getArray();
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			appendAsString(sb, 0);
			return sb.toString();
		}

		public void appendAsString(StringBuilder sb, int indentLevel) {

			sb.append("{\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			if (seqOf == null) {
				sb.append("null");
			}
			else {
				Iterator<chargingcdr.ServiceClassInfo> it = seqOf.iterator();
				if (it.hasNext()) {
					it.next().appendAsString(sb, indentLevel + 1);
					while (it.hasNext()) {
						sb.append(",\n");
						for (int i = 0; i < indentLevel + 1; i++) {
							sb.append("\t");
						}
						it.next().appendAsString(sb, indentLevel + 1);
					}
				}
			}

			sb.append("\n");
			for (int i = 0; i < indentLevel; i++) {
				sb.append("\t");
			}
			sb.append("}");
		}

	}

	public static class ChargingID implements Serializable {

		private static final long serialVersionUID = 1L;

		public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
		public byte[] code = null;
		private List<chargingcdr.ChargingID> seqOf = null;

		public ChargingID() {
			seqOf = new ArrayList<chargingcdr.ChargingID>();
		}

		public ChargingID(byte[] code) {
			this.code = code;
		}

		public List<chargingcdr.ChargingID> getChargingID() {
			if (seqOf == null) {
				seqOf = new ArrayList<chargingcdr.ChargingID>();
			}
			return seqOf;
		}

		public int encode(OutputStream os) throws IOException {
			return encode(os, true);
		}

		public int encode(OutputStream os, boolean withTag) throws IOException {

			if (code != null) {
				for (int i = code.length - 1; i >= 0; i--) {
					os.write(code[i]);
				}
				if (withTag) {
					return tag.encode(os) + code.length;
				}
				return code.length;
			}

			int codeLength = 0;
			for (int i = (seqOf.size() - 1); i >= 0; i--) {
				codeLength += seqOf.get(i).encode(os, true);
			}

			codeLength += BerLength.encodeLength(os, codeLength);

			if (withTag) {
				codeLength += tag.encode(os);
			}

			return codeLength;
		}

		public int decode(InputStream is) throws IOException {
			return decode(is, true);
		}

		public int decode(InputStream is, boolean withTag) throws IOException {
			int codeLength = 0;
			int subCodeLength = 0;
			if (withTag) {
				codeLength += tag.decodeAndCheck(is);
			}

			BerLength length = new BerLength();
			codeLength += length.decode(is);
			int totalLength = length.val;

			while (subCodeLength < totalLength) {
				chargingcdr.ChargingID element = new chargingcdr.ChargingID();
				subCodeLength += element.decode(is, true);
				seqOf.add(element);
			}
			if (subCodeLength != totalLength) {
				throw new IOException("Decoded SequenceOf or SetOf has wrong length. Expected " + totalLength + " but has " + subCodeLength);

			}
			codeLength += subCodeLength;

			return codeLength;
		}

		public void encodeAndSave(int encodingSizeGuess) throws IOException {
			ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
			encode(os, false);
			code = os.getArray();
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			appendAsString(sb, 0);
			return sb.toString();
		}

		public void appendAsString(StringBuilder sb, int indentLevel) {

			sb.append("{\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			if (seqOf == null) {
				sb.append("null");
			}
			else {
				Iterator<chargingcdr.ChargingID> it = seqOf.iterator();
				if (it.hasNext()) {
					sb.append(it.next());
					while (it.hasNext()) {
						sb.append(",\n");
						for (int i = 0; i < indentLevel + 1; i++) {
							sb.append("\t");
						}
						sb.append(it.next());
					}
				}
			}

			sb.append("\n");
			for (int i = 0; i < indentLevel; i++) {
				sb.append("\t");
			}
			sb.append("}");
		}

	}

	public static class ServiceClassExtendedInfo implements Serializable {

		private static final long serialVersionUID = 1L;

		public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
		public byte[] code = null;
		private List<chargingcdr.ServiceClassExtendedInfo> seqOf = null;

		public ServiceClassExtendedInfo() {
			seqOf = new ArrayList<chargingcdr.ServiceClassExtendedInfo>();
		}

		public ServiceClassExtendedInfo(byte[] code) {
			this.code = code;
		}

		public List<chargingcdr.ServiceClassExtendedInfo> getServiceClassExtendedInfo() {
			if (seqOf == null) {
				seqOf = new ArrayList<chargingcdr.ServiceClassExtendedInfo>();
			}
			return seqOf;
		}

		public int encode(OutputStream os) throws IOException {
			return encode(os, true);
		}

		public int encode(OutputStream os, boolean withTag) throws IOException {

			if (code != null) {
				for (int i = code.length - 1; i >= 0; i--) {
					os.write(code[i]);
				}
				if (withTag) {
					return tag.encode(os) + code.length;
				}
				return code.length;
			}

			int codeLength = 0;
			for (int i = (seqOf.size() - 1); i >= 0; i--) {
				codeLength += seqOf.get(i).encode(os, true);
			}

			codeLength += BerLength.encodeLength(os, codeLength);

			if (withTag) {
				codeLength += tag.encode(os);
			}

			return codeLength;
		}

		public int decode(InputStream is) throws IOException {
			return decode(is, true);
		}

		public int decode(InputStream is, boolean withTag) throws IOException {
			int codeLength = 0;
			int subCodeLength = 0;
			if (withTag) {
				codeLength += tag.decodeAndCheck(is);
			}

			BerLength length = new BerLength();
			codeLength += length.decode(is);
			int totalLength = length.val;

			while (subCodeLength < totalLength) {
				chargingcdr.ServiceClassExtendedInfo element = new chargingcdr.ServiceClassExtendedInfo();
				subCodeLength += element.decode(is, true);
				seqOf.add(element);
			}
			if (subCodeLength != totalLength) {
				throw new IOException("Decoded SequenceOf or SetOf has wrong length. Expected " + totalLength + " but has " + subCodeLength);

			}
			codeLength += subCodeLength;

			return codeLength;
		}

		public void encodeAndSave(int encodingSizeGuess) throws IOException {
			ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
			encode(os, false);
			code = os.getArray();
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			appendAsString(sb, 0);
			return sb.toString();
		}

		public void appendAsString(StringBuilder sb, int indentLevel) {

			sb.append("{\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			if (seqOf == null) {
				sb.append("null");
			}
			else {
				Iterator<chargingcdr.ServiceClassExtendedInfo> it = seqOf.iterator();
				if (it.hasNext()) {
					it.next().appendAsString(sb, indentLevel + 1);
					while (it.hasNext()) {
						sb.append(",\n");
						for (int i = 0; i < indentLevel + 1; i++) {
							sb.append("\t");
						}
						it.next().appendAsString(sb, indentLevel + 1);
					}
				}
			}

			sb.append("\n");
			for (int i = 0; i < indentLevel; i++) {
				sb.append("\t");
			}
			sb.append("}");
		}

	}

	public static class StartOfUsageInfo implements Serializable {

		private static final long serialVersionUID = 1L;

		public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
		public byte[] code = null;
		private List<chargingcdr.StartOfUsageInfo> seqOf = null;

		public StartOfUsageInfo() {
			seqOf = new ArrayList<chargingcdr.StartOfUsageInfo>();
		}

		public StartOfUsageInfo(byte[] code) {
			this.code = code;
		}

		public List<chargingcdr.StartOfUsageInfo> getStartOfUsageInfo() {
			if (seqOf == null) {
				seqOf = new ArrayList<chargingcdr.StartOfUsageInfo>();
			}
			return seqOf;
		}

		public int encode(OutputStream os) throws IOException {
			return encode(os, true);
		}

		public int encode(OutputStream os, boolean withTag) throws IOException {

			if (code != null) {
				for (int i = code.length - 1; i >= 0; i--) {
					os.write(code[i]);
				}
				if (withTag) {
					return tag.encode(os) + code.length;
				}
				return code.length;
			}

			int codeLength = 0;
			for (int i = (seqOf.size() - 1); i >= 0; i--) {
				codeLength += seqOf.get(i).encode(os, true);
			}

			codeLength += BerLength.encodeLength(os, codeLength);

			if (withTag) {
				codeLength += tag.encode(os);
			}

			return codeLength;
		}

		public int decode(InputStream is) throws IOException {
			return decode(is, true);
		}

		public int decode(InputStream is, boolean withTag) throws IOException {
			int codeLength = 0;
			int subCodeLength = 0;
			if (withTag) {
				codeLength += tag.decodeAndCheck(is);
			}

			BerLength length = new BerLength();
			codeLength += length.decode(is);
			int totalLength = length.val;

			while (subCodeLength < totalLength) {
				chargingcdr.StartOfUsageInfo element = new chargingcdr.StartOfUsageInfo();
				subCodeLength += element.decode(is, true);
				seqOf.add(element);
			}
			if (subCodeLength != totalLength) {
				throw new IOException("Decoded SequenceOf or SetOf has wrong length. Expected " + totalLength + " but has " + subCodeLength);

			}
			codeLength += subCodeLength;

			return codeLength;
		}

		public void encodeAndSave(int encodingSizeGuess) throws IOException {
			ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
			encode(os, false);
			code = os.getArray();
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			appendAsString(sb, 0);
			return sb.toString();
		}

		public void appendAsString(StringBuilder sb, int indentLevel) {

			sb.append("{\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			if (seqOf == null) {
				sb.append("null");
			}
			else {
				Iterator<chargingcdr.StartOfUsageInfo> it = seqOf.iterator();
				if (it.hasNext()) {
					it.next().appendAsString(sb, indentLevel + 1);
					while (it.hasNext()) {
						sb.append(",\n");
						for (int i = 0; i < indentLevel + 1; i++) {
							sb.append("\t");
						}
						it.next().appendAsString(sb, indentLevel + 1);
					}
				}
			}

			sb.append("\n");
			for (int i = 0; i < indentLevel; i++) {
				sb.append("\t");
			}
			sb.append("}");
		}

	}

	public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

	public byte[] code = null;
	private ServiceClassInfo serviceClassInfo = null;
	private GSNAddress ggsnAddressUsed = null;
	private ChargingID chargingID = null;
	private TimeStamp timeStamp = null;
	private DataVolume volumeULAccumulatedTotal = null;
	private DataVolume volumeDLAccumulatedTotal = null;
	private SubscriberGroup subscriberGroup = null;
	private NodeID nodeID = null;
	private LocalSequenceNumber localSequenceNumber = null;
	private IMSI servedIMSI = null;
	private AddressString servedMSISDN = null;
	private UserName userName = null;
	private RecoveryRecord recoveryData = null;
	private ServiceClassExtendedInfo serviceClassExtendedInfo = null;
	private BerBoolean ratingRequested = null;
	private BerBoolean ratingSuccessful = null;
	private StartOfUsageInfo startOfUsageInfo = null;
	
	public FBCRatingRecord() {
	}

	public FBCRatingRecord(byte[] code) {
		this.code = code;
	}

	public void setServiceClassInfo(ServiceClassInfo serviceClassInfo) {
		this.serviceClassInfo = serviceClassInfo;
	}

	public ServiceClassInfo getServiceClassInfo() {
		return serviceClassInfo;
	}

	public void setGgsnAddressUsed(GSNAddress ggsnAddressUsed) {
		this.ggsnAddressUsed = ggsnAddressUsed;
	}

	public GSNAddress getGgsnAddressUsed() {
		return ggsnAddressUsed;
	}

	public void setChargingID(ChargingID chargingID) {
		this.chargingID = chargingID;
	}

	public ChargingID getChargingID() {
		return chargingID;
	}

	public void setTimeStamp(TimeStamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public TimeStamp getTimeStamp() {
		return timeStamp;
	}

	public void setVolumeULAccumulatedTotal(DataVolume volumeULAccumulatedTotal) {
		this.volumeULAccumulatedTotal = volumeULAccumulatedTotal;
	}

	public DataVolume getVolumeULAccumulatedTotal() {
		return volumeULAccumulatedTotal;
	}

	public void setVolumeDLAccumulatedTotal(DataVolume volumeDLAccumulatedTotal) {
		this.volumeDLAccumulatedTotal = volumeDLAccumulatedTotal;
	}

	public DataVolume getVolumeDLAccumulatedTotal() {
		return volumeDLAccumulatedTotal;
	}

	public void setSubscriberGroup(SubscriberGroup subscriberGroup) {
		this.subscriberGroup = subscriberGroup;
	}

	public SubscriberGroup getSubscriberGroup() {
		return subscriberGroup;
	}

	public void setNodeID(NodeID nodeID) {
		this.nodeID = nodeID;
	}

	public NodeID getNodeID() {
		return nodeID;
	}

	public void setLocalSequenceNumber(LocalSequenceNumber localSequenceNumber) {
		this.localSequenceNumber = localSequenceNumber;
	}

	public LocalSequenceNumber getLocalSequenceNumber() {
		return localSequenceNumber;
	}

	public void setServedIMSI(IMSI servedIMSI) {
		this.servedIMSI = servedIMSI;
	}

	public IMSI getServedIMSI() {
		return servedIMSI;
	}

	public void setServedMSISDN(AddressString servedMSISDN) {
		this.servedMSISDN = servedMSISDN;
	}

	public AddressString getServedMSISDN() {
		return servedMSISDN;
	}

	public void setUserName(UserName userName) {
		this.userName = userName;
	}

	public UserName getUserName() {
		return userName;
	}

	public void setRecoveryData(RecoveryRecord recoveryData) {
		this.recoveryData = recoveryData;
	}

	public RecoveryRecord getRecoveryData() {
		return recoveryData;
	}

	public void setServiceClassExtendedInfo(ServiceClassExtendedInfo serviceClassExtendedInfo) {
		this.serviceClassExtendedInfo = serviceClassExtendedInfo;
	}

	public ServiceClassExtendedInfo getServiceClassExtendedInfo() {
		return serviceClassExtendedInfo;
	}

	public void setRatingRequested(BerBoolean ratingRequested) {
		this.ratingRequested = ratingRequested;
	}

	public BerBoolean getRatingRequested() {
		return ratingRequested;
	}

	public void setRatingSuccessful(BerBoolean ratingSuccessful) {
		this.ratingSuccessful = ratingSuccessful;
	}

	public BerBoolean getRatingSuccessful() {
		return ratingSuccessful;
	}

	public void setStartOfUsageInfo(StartOfUsageInfo startOfUsageInfo) {
		this.startOfUsageInfo = startOfUsageInfo;
	}

	public StartOfUsageInfo getStartOfUsageInfo() {
		return startOfUsageInfo;
	}

	public int encode(OutputStream os) throws IOException {
		return encode(os, true);
	}

	public int encode(OutputStream os, boolean withTag) throws IOException {

		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				os.write(code[i]);
			}
			if (withTag) {
				return tag.encode(os) + code.length;
			}
			return code.length;
		}

		int codeLength = 0;
		int sublength;

		if (startOfUsageInfo != null) {
			codeLength += startOfUsageInfo.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 16
			os.write(0xB0);
			codeLength += 1;
		}
		
		if (ratingSuccessful != null) {
			codeLength += ratingSuccessful.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 15
			os.write(0x8F);
			codeLength += 1;
		}
		
		if (ratingRequested != null) {
			codeLength += ratingRequested.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 14
			os.write(0x8E);
			codeLength += 1;
		}
		
		if (serviceClassExtendedInfo != null) {
			codeLength += serviceClassExtendedInfo.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 13
			os.write(0xAD);
			codeLength += 1;
		}
		
		if (recoveryData != null) {
			codeLength += recoveryData.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 12
			os.write(0xAC);
			codeLength += 1;
		}
		
		if (userName != null) {
			codeLength += userName.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 11
			os.write(0x8B);
			codeLength += 1;
		}
		
		if (servedMSISDN != null) {
			codeLength += servedMSISDN.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 10
			os.write(0x8A);
			codeLength += 1;
		}
		
		if (servedIMSI != null) {
			codeLength += servedIMSI.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 9
			os.write(0x89);
			codeLength += 1;
		}
		
		codeLength += localSequenceNumber.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 8
		os.write(0x88);
		codeLength += 1;
		
		codeLength += nodeID.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 7
		os.write(0x87);
		codeLength += 1;
		
		codeLength += subscriberGroup.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 6
		os.write(0x86);
		codeLength += 1;
		
		codeLength += volumeDLAccumulatedTotal.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 5
		os.write(0x85);
		codeLength += 1;
		
		codeLength += volumeULAccumulatedTotal.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 4
		os.write(0x84);
		codeLength += 1;
		
		codeLength += timeStamp.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 3
		os.write(0x83);
		codeLength += 1;
		
		codeLength += chargingID.encode(os, false);
		// write tag: CONTEXT_CLASS, CONSTRUCTED, 2
		os.write(0xA2);
		codeLength += 1;
		
		sublength = ggsnAddressUsed.encode(os);
		codeLength += sublength;
		codeLength += BerLength.encodeLength(os, sublength);
		// write tag: CONTEXT_CLASS, CONSTRUCTED, 1
		os.write(0xA1);
		codeLength += 1;
		
		codeLength += serviceClassInfo.encode(os, false);
		// write tag: CONTEXT_CLASS, CONSTRUCTED, 0
		os.write(0xA0);
		codeLength += 1;
		
		codeLength += BerLength.encodeLength(os, codeLength);

		if (withTag) {
			codeLength += tag.encode(os);
		}

		return codeLength;

	}

	public int decode(InputStream is) throws IOException {
		return decode(is, true);
	}

	public int decode(InputStream is, boolean withTag) throws IOException {
		int codeLength = 0;
		int subCodeLength = 0;
		BerTag berTag = new BerTag();

		if (withTag) {
			codeLength += tag.decodeAndCheck(is);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(is);

		int totalLength = length.val;
		codeLength += totalLength;

		subCodeLength += berTag.decode(is);
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
			serviceClassInfo = new ServiceClassInfo();
			subCodeLength += serviceClassInfo.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
			subCodeLength += length.decode(is);
			ggsnAddressUsed = new GSNAddress();
			subCodeLength += ggsnAddressUsed.decode(is, null);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 2)) {
			chargingID = new ChargingID();
			subCodeLength += chargingID.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 3)) {
			timeStamp = new TimeStamp();
			subCodeLength += timeStamp.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 4)) {
			volumeULAccumulatedTotal = new DataVolume();
			subCodeLength += volumeULAccumulatedTotal.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 5)) {
			volumeDLAccumulatedTotal = new DataVolume();
			subCodeLength += volumeDLAccumulatedTotal.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 6)) {
			subscriberGroup = new SubscriberGroup();
			subCodeLength += subscriberGroup.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 7)) {
			nodeID = new NodeID();
			subCodeLength += nodeID.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 8)) {
			localSequenceNumber = new LocalSequenceNumber();
			subCodeLength += localSequenceNumber.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 9)) {
			servedIMSI = new IMSI();
			subCodeLength += servedIMSI.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 10)) {
			servedMSISDN = new AddressString();
			subCodeLength += servedMSISDN.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 11)) {
			userName = new UserName();
			subCodeLength += userName.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 12)) {
			recoveryData = new RecoveryRecord();
			subCodeLength += recoveryData.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 13)) {
			serviceClassExtendedInfo = new ServiceClassExtendedInfo();
			subCodeLength += serviceClassExtendedInfo.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 14)) {
			ratingRequested = new BerBoolean();
			subCodeLength += ratingRequested.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 15)) {
			ratingSuccessful = new BerBoolean();
			subCodeLength += ratingSuccessful.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 16)) {
			startOfUsageInfo = new StartOfUsageInfo();
			subCodeLength += startOfUsageInfo.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
		}
		throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

		
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
		encode(os, false);
		code = os.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		sb.append("{");
		sb.append("\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (serviceClassInfo != null) {
			sb.append("serviceClassInfo: ");
			serviceClassInfo.appendAsString(sb, indentLevel + 1);
		}
		else {
			sb.append("serviceClassInfo: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (ggsnAddressUsed != null) {
			sb.append("ggsnAddressUsed: ");
			ggsnAddressUsed.appendAsString(sb, indentLevel + 1);
		}
		else {
			sb.append("ggsnAddressUsed: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (chargingID != null) {
			sb.append("chargingID: ");
			chargingID.appendAsString(sb, indentLevel + 1);
		}
		else {
			sb.append("chargingID: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (timeStamp != null) {
			sb.append("timeStamp: ").append(timeStamp);
		}
		else {
			sb.append("timeStamp: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (volumeULAccumulatedTotal != null) {
			sb.append("volumeULAccumulatedTotal: ").append(volumeULAccumulatedTotal);
		}
		else {
			sb.append("volumeULAccumulatedTotal: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (volumeDLAccumulatedTotal != null) {
			sb.append("volumeDLAccumulatedTotal: ").append(volumeDLAccumulatedTotal);
		}
		else {
			sb.append("volumeDLAccumulatedTotal: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (subscriberGroup != null) {
			sb.append("subscriberGroup: ").append(subscriberGroup);
		}
		else {
			sb.append("subscriberGroup: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (nodeID != null) {
			sb.append("nodeID: ").append(nodeID);
		}
		else {
			sb.append("nodeID: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (localSequenceNumber != null) {
			sb.append("localSequenceNumber: ").append(localSequenceNumber);
		}
		else {
			sb.append("localSequenceNumber: <empty-required-field>");
		}
		
		if (servedIMSI != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("servedIMSI: ").append(servedIMSI);
		}
		
		if (servedMSISDN != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("servedMSISDN: ").append(servedMSISDN);
		}
		
		if (userName != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("userName: ").append(userName);
		}
		
		if (recoveryData != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("recoveryData: ");
			recoveryData.appendAsString(sb, indentLevel + 1);
		}
		
		if (serviceClassExtendedInfo != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("serviceClassExtendedInfo: ");
			serviceClassExtendedInfo.appendAsString(sb, indentLevel + 1);
		}
		
		if (ratingRequested != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("ratingRequested: ").append(ratingRequested);
		}
		
		if (ratingSuccessful != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("ratingSuccessful: ").append(ratingSuccessful);
		}
		
		if (startOfUsageInfo != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("startOfUsageInfo: ");
			startOfUsageInfo.appendAsString(sb, indentLevel + 1);
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}
