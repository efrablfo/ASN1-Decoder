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


public class ProviderCCAccountData implements Serializable {

	private static final long serialVersionUID = 1L;

	public static class UsageCounters implements Serializable {

		private static final long serialVersionUID = 1L;

		public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
		public byte[] code = null;
		private List<UsageCounter> seqOf = null;

		public UsageCounters() {
			seqOf = new ArrayList<UsageCounter>();
		}

		public UsageCounters(byte[] code) {
			this.code = code;
		}

		public List<UsageCounter> getUsageCounter() {
			if (seqOf == null) {
				seqOf = new ArrayList<UsageCounter>();
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
				UsageCounter element = new UsageCounter();
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
				Iterator<UsageCounter> it = seqOf.iterator();
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
	private AddressString providerAccount = null;
	private ServiceClassID providerServiceClassID = null;
	private AccountGroupID accountGroupID = null;
	private DedicatedAccounts dedicatedAccounts = null;
	private FamilyAndFriendsID familyAndFriendsID = null;
	private AddressString familyAndFriendsNo = null;
	private MonetaryUnits accumulatedCost = null;
	private MonetaryUnits accountValueUsed = null;
	private MonetaryUnits accountValueDeducted = null;
	private Integer64 accumulatedUnits = null;
	private Integer64 accountUnitsDeducted = null;
	private SpecifiedConsumptions specifiedConsumptions = null;
	private UsageCounters usageCounters = null;
	private FamilyAndFriendsID providersFamilyAndFriendsID = null;
	
	public ProviderCCAccountData() {
	}

	public ProviderCCAccountData(byte[] code) {
		this.code = code;
	}

	public void setProviderAccount(AddressString providerAccount) {
		this.providerAccount = providerAccount;
	}

	public AddressString getProviderAccount() {
		return providerAccount;
	}

	public void setProviderServiceClassID(ServiceClassID providerServiceClassID) {
		this.providerServiceClassID = providerServiceClassID;
	}

	public ServiceClassID getProviderServiceClassID() {
		return providerServiceClassID;
	}

	public void setAccountGroupID(AccountGroupID accountGroupID) {
		this.accountGroupID = accountGroupID;
	}

	public AccountGroupID getAccountGroupID() {
		return accountGroupID;
	}

	public void setDedicatedAccounts(DedicatedAccounts dedicatedAccounts) {
		this.dedicatedAccounts = dedicatedAccounts;
	}

	public DedicatedAccounts getDedicatedAccounts() {
		return dedicatedAccounts;
	}

	public void setFamilyAndFriendsID(FamilyAndFriendsID familyAndFriendsID) {
		this.familyAndFriendsID = familyAndFriendsID;
	}

	public FamilyAndFriendsID getFamilyAndFriendsID() {
		return familyAndFriendsID;
	}

	public void setFamilyAndFriendsNo(AddressString familyAndFriendsNo) {
		this.familyAndFriendsNo = familyAndFriendsNo;
	}

	public AddressString getFamilyAndFriendsNo() {
		return familyAndFriendsNo;
	}

	public void setAccumulatedCost(MonetaryUnits accumulatedCost) {
		this.accumulatedCost = accumulatedCost;
	}

	public MonetaryUnits getAccumulatedCost() {
		return accumulatedCost;
	}

	public void setAccountValueUsed(MonetaryUnits accountValueUsed) {
		this.accountValueUsed = accountValueUsed;
	}

	public MonetaryUnits getAccountValueUsed() {
		return accountValueUsed;
	}

	public void setAccountValueDeducted(MonetaryUnits accountValueDeducted) {
		this.accountValueDeducted = accountValueDeducted;
	}

	public MonetaryUnits getAccountValueDeducted() {
		return accountValueDeducted;
	}

	public void setAccumulatedUnits(Integer64 accumulatedUnits) {
		this.accumulatedUnits = accumulatedUnits;
	}

	public Integer64 getAccumulatedUnits() {
		return accumulatedUnits;
	}

	public void setAccountUnitsDeducted(Integer64 accountUnitsDeducted) {
		this.accountUnitsDeducted = accountUnitsDeducted;
	}

	public Integer64 getAccountUnitsDeducted() {
		return accountUnitsDeducted;
	}

	public void setSpecifiedConsumptions(SpecifiedConsumptions specifiedConsumptions) {
		this.specifiedConsumptions = specifiedConsumptions;
	}

	public SpecifiedConsumptions getSpecifiedConsumptions() {
		return specifiedConsumptions;
	}

	public void setUsageCounters(UsageCounters usageCounters) {
		this.usageCounters = usageCounters;
	}

	public UsageCounters getUsageCounters() {
		return usageCounters;
	}

	public void setProvidersFamilyAndFriendsID(FamilyAndFriendsID providersFamilyAndFriendsID) {
		this.providersFamilyAndFriendsID = providersFamilyAndFriendsID;
	}

	public FamilyAndFriendsID getProvidersFamilyAndFriendsID() {
		return providersFamilyAndFriendsID;
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
		if (providersFamilyAndFriendsID != null) {
			codeLength += providersFamilyAndFriendsID.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 14
			os.write(0x8E);
			codeLength += 1;
		}
		
		if (usageCounters != null) {
			codeLength += usageCounters.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 13
			os.write(0xAD);
			codeLength += 1;
		}
		
		if (specifiedConsumptions != null) {
			codeLength += specifiedConsumptions.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 12
			os.write(0xAC);
			codeLength += 1;
		}
		
		if (accountUnitsDeducted != null) {
			codeLength += accountUnitsDeducted.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 11
			os.write(0x8B);
			codeLength += 1;
		}
		
		if (accumulatedUnits != null) {
			codeLength += accumulatedUnits.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 9
			os.write(0x89);
			codeLength += 1;
		}
		
		codeLength += accountValueDeducted.encode(os, false);
		// write tag: CONTEXT_CLASS, CONSTRUCTED, 8
		os.write(0xA8);
		codeLength += 1;
		
		if (accountValueUsed != null) {
			codeLength += accountValueUsed.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 7
			os.write(0xA7);
			codeLength += 1;
		}
		
		if (accumulatedCost != null) {
			codeLength += accumulatedCost.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 6
			os.write(0xA6);
			codeLength += 1;
		}
		
		if (familyAndFriendsNo != null) {
			codeLength += familyAndFriendsNo.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 5
			os.write(0x85);
			codeLength += 1;
		}
		
		if (familyAndFriendsID != null) {
			codeLength += familyAndFriendsID.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 4
			os.write(0x84);
			codeLength += 1;
		}
		
		if (dedicatedAccounts != null) {
			codeLength += dedicatedAccounts.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 3
			os.write(0xA3);
			codeLength += 1;
		}
		
		codeLength += accountGroupID.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 2
		os.write(0x82);
		codeLength += 1;
		
		codeLength += providerServiceClassID.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 1
		os.write(0x81);
		codeLength += 1;
		
		codeLength += providerAccount.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 0
		os.write(0x80);
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
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
			providerAccount = new AddressString();
			subCodeLength += providerAccount.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
			providerServiceClassID = new ServiceClassID();
			subCodeLength += providerServiceClassID.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 2)) {
			accountGroupID = new AccountGroupID();
			subCodeLength += accountGroupID.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 3)) {
			dedicatedAccounts = new DedicatedAccounts();
			subCodeLength += dedicatedAccounts.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 4)) {
			familyAndFriendsID = new FamilyAndFriendsID();
			subCodeLength += familyAndFriendsID.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 5)) {
			familyAndFriendsNo = new AddressString();
			subCodeLength += familyAndFriendsNo.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 6)) {
			accumulatedCost = new MonetaryUnits();
			subCodeLength += accumulatedCost.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 7)) {
			accountValueUsed = new MonetaryUnits();
			subCodeLength += accountValueUsed.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 8)) {
			accountValueDeducted = new MonetaryUnits();
			subCodeLength += accountValueDeducted.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 9)) {
			accumulatedUnits = new Integer64();
			subCodeLength += accumulatedUnits.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 11)) {
			accountUnitsDeducted = new Integer64();
			subCodeLength += accountUnitsDeducted.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 12)) {
			specifiedConsumptions = new SpecifiedConsumptions();
			subCodeLength += specifiedConsumptions.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 13)) {
			usageCounters = new UsageCounters();
			subCodeLength += usageCounters.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 14)) {
			providersFamilyAndFriendsID = new FamilyAndFriendsID();
			subCodeLength += providersFamilyAndFriendsID.decode(is, false);
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
		if (providerAccount != null) {
			sb.append("providerAccount: ").append(providerAccount);
		}
		else {
			sb.append("providerAccount: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (providerServiceClassID != null) {
			sb.append("providerServiceClassID: ").append(providerServiceClassID);
		}
		else {
			sb.append("providerServiceClassID: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (accountGroupID != null) {
			sb.append("accountGroupID: ").append(accountGroupID);
		}
		else {
			sb.append("accountGroupID: <empty-required-field>");
		}
		
		if (dedicatedAccounts != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("dedicatedAccounts: ");
			dedicatedAccounts.appendAsString(sb, indentLevel + 1);
		}
		
		if (familyAndFriendsID != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("familyAndFriendsID: ").append(familyAndFriendsID);
		}
		
		if (familyAndFriendsNo != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("familyAndFriendsNo: ").append(familyAndFriendsNo);
		}
		
		if (accumulatedCost != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("accumulatedCost: ");
			accumulatedCost.appendAsString(sb, indentLevel + 1);
		}
		
		if (accountValueUsed != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("accountValueUsed: ");
			accountValueUsed.appendAsString(sb, indentLevel + 1);
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (accountValueDeducted != null) {
			sb.append("accountValueDeducted: ");
			accountValueDeducted.appendAsString(sb, indentLevel + 1);
		}
		else {
			sb.append("accountValueDeducted: <empty-required-field>");
		}
		
		if (accumulatedUnits != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("accumulatedUnits: ").append(accumulatedUnits);
		}
		
		if (accountUnitsDeducted != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("accountUnitsDeducted: ").append(accountUnitsDeducted);
		}
		
		if (specifiedConsumptions != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("specifiedConsumptions: ");
			specifiedConsumptions.appendAsString(sb, indentLevel + 1);
		}
		
		if (usageCounters != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("usageCounters: ");
			usageCounters.appendAsString(sb, indentLevel + 1);
		}
		
		if (providersFamilyAndFriendsID != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("providersFamilyAndFriendsID: ").append(providersFamilyAndFriendsID);
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}
