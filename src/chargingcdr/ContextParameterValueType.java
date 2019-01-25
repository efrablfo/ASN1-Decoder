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


public class ContextParameterValueType implements Serializable {

	private static final long serialVersionUID = 1L;

	public byte[] code = null;
	private BerBoolean boolean_ = null;
	private Integer32 integer32 = null;
	private Integer64 integer64 = null;
	private Unsigned32 unsigned32 = null;
	private Unsigned64 unsigned64 = null;
	private AddressString addressString = null;
	private BerOctetString octetString = null;
	private BerUTF8String string = null;
	private Time time = null;
	private PartyInformation partyInformation = null;
	private Group group = null;
	private PartyInformations partyInformations = null;
	private Strings strings = null;
	private Groups groups = null;
	private MonetaryUnits monetaryUnits = null;
	
	public ContextParameterValueType() {
	}

	public ContextParameterValueType(byte[] code) {
		this.code = code;
	}

	public void setBoolean(BerBoolean boolean_) {
		this.boolean_ = boolean_;
	}

	public BerBoolean getBoolean() {
		return boolean_;
	}

	public void setInteger32(Integer32 integer32) {
		this.integer32 = integer32;
	}

	public Integer32 getInteger32() {
		return integer32;
	}

	public void setInteger64(Integer64 integer64) {
		this.integer64 = integer64;
	}

	public Integer64 getInteger64() {
		return integer64;
	}

	public void setUnsigned32(Unsigned32 unsigned32) {
		this.unsigned32 = unsigned32;
	}

	public Unsigned32 getUnsigned32() {
		return unsigned32;
	}

	public void setUnsigned64(Unsigned64 unsigned64) {
		this.unsigned64 = unsigned64;
	}

	public Unsigned64 getUnsigned64() {
		return unsigned64;
	}

	public void setAddressString(AddressString addressString) {
		this.addressString = addressString;
	}

	public AddressString getAddressString() {
		return addressString;
	}

	public void setOctetString(BerOctetString octetString) {
		this.octetString = octetString;
	}

	public BerOctetString getOctetString() {
		return octetString;
	}

	public void setString(BerUTF8String string) {
		this.string = string;
	}

	public BerUTF8String getString() {
		return string;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Time getTime() {
		return time;
	}

	public void setPartyInformation(PartyInformation partyInformation) {
		this.partyInformation = partyInformation;
	}

	public PartyInformation getPartyInformation() {
		return partyInformation;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Group getGroup() {
		return group;
	}

	public void setPartyInformations(PartyInformations partyInformations) {
		this.partyInformations = partyInformations;
	}

	public PartyInformations getPartyInformations() {
		return partyInformations;
	}

	public void setStrings(Strings strings) {
		this.strings = strings;
	}

	public Strings getStrings() {
		return strings;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

	public Groups getGroups() {
		return groups;
	}

	public void setMonetaryUnits(MonetaryUnits monetaryUnits) {
		this.monetaryUnits = monetaryUnits;
	}

	public MonetaryUnits getMonetaryUnits() {
		return monetaryUnits;
	}

	public int encode(OutputStream os) throws IOException {

		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				os.write(code[i]);
			}
			return code.length;
		}

		int codeLength = 0;
		int sublength;

		if (monetaryUnits != null) {
			codeLength += monetaryUnits.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 14
			os.write(0xAE);
			codeLength += 1;
			return codeLength;
		}
		
		if (groups != null) {
			codeLength += groups.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 13
			os.write(0xAD);
			codeLength += 1;
			return codeLength;
		}
		
		if (strings != null) {
			codeLength += strings.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 12
			os.write(0xAC);
			codeLength += 1;
			return codeLength;
		}
		
		if (partyInformations != null) {
			codeLength += partyInformations.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 11
			os.write(0xAB);
			codeLength += 1;
			return codeLength;
		}
		
		if (group != null) {
			codeLength += group.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 10
			os.write(0xAA);
			codeLength += 1;
			return codeLength;
		}
		
		if (partyInformation != null) {
			sublength = partyInformation.encode(os);
			codeLength += sublength;
			codeLength += BerLength.encodeLength(os, sublength);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 9
			os.write(0xA9);
			codeLength += 1;
			return codeLength;
		}
		
		if (time != null) {
			codeLength += time.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 8
			os.write(0xA8);
			codeLength += 1;
			return codeLength;
		}
		
		if (string != null) {
			codeLength += string.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 7
			os.write(0x87);
			codeLength += 1;
			return codeLength;
		}
		
		if (octetString != null) {
			codeLength += octetString.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 6
			os.write(0x86);
			codeLength += 1;
			return codeLength;
		}
		
		if (addressString != null) {
			codeLength += addressString.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 5
			os.write(0x85);
			codeLength += 1;
			return codeLength;
		}
		
		if (unsigned64 != null) {
			codeLength += unsigned64.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 4
			os.write(0x84);
			codeLength += 1;
			return codeLength;
		}
		
		if (unsigned32 != null) {
			codeLength += unsigned32.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 3
			os.write(0x83);
			codeLength += 1;
			return codeLength;
		}
		
		if (integer64 != null) {
			codeLength += integer64.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 2
			os.write(0x82);
			codeLength += 1;
			return codeLength;
		}
		
		if (integer32 != null) {
			codeLength += integer32.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 1
			os.write(0x81);
			codeLength += 1;
			return codeLength;
		}
		
		if (boolean_ != null) {
			codeLength += boolean_.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 0
			os.write(0x80);
			codeLength += 1;
			return codeLength;
		}
		
		throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
	}

	public int decode(InputStream is) throws IOException {
		return decode(is, null);
	}

	public int decode(InputStream is, BerTag berTag) throws IOException {

		int codeLength = 0;
		BerTag passedTag = berTag;

		if (berTag == null) {
			berTag = new BerTag();
			codeLength += berTag.decode(is);
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
			boolean_ = new BerBoolean();
			codeLength += boolean_.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
			integer32 = new Integer32();
			codeLength += integer32.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 2)) {
			integer64 = new Integer64();
			codeLength += integer64.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 3)) {
			unsigned32 = new Unsigned32();
			codeLength += unsigned32.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 4)) {
			unsigned64 = new Unsigned64();
			codeLength += unsigned64.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 5)) {
			addressString = new AddressString();
			codeLength += addressString.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 6)) {
			octetString = new BerOctetString();
			codeLength += octetString.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 7)) {
			string = new BerUTF8String();
			codeLength += string.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 8)) {
			time = new Time();
			codeLength += time.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 9)) {
			codeLength += BerLength.skip(is);
			partyInformation = new PartyInformation();
			codeLength += partyInformation.decode(is, null);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 10)) {
			group = new Group();
			codeLength += group.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 11)) {
			partyInformations = new PartyInformations();
			codeLength += partyInformations.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 12)) {
			strings = new Strings();
			codeLength += strings.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 13)) {
			groups = new Groups();
			codeLength += groups.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 14)) {
			monetaryUnits = new MonetaryUnits();
			codeLength += monetaryUnits.decode(is, false);
			return codeLength;
		}

		if (passedTag != null) {
			return 0;
		}

		throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
		encode(os);
		code = os.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		if (boolean_ != null) {
			sb.append("boolean_: ").append(boolean_);
			return;
		}

		if (integer32 != null) {
			sb.append("integer32: ").append(integer32);
			return;
		}

		if (integer64 != null) {
			sb.append("integer64: ").append(integer64);
			return;
		}

		if (unsigned32 != null) {
			sb.append("unsigned32: ").append(unsigned32);
			return;
		}

		if (unsigned64 != null) {
			sb.append("unsigned64: ").append(unsigned64);
			return;
		}

		if (addressString != null) {
			sb.append("addressString: ").append(addressString);
			return;
		}

		if (octetString != null) {
			sb.append("octetString: ").append(octetString);
			return;
		}

		if (string != null) {
			sb.append("string: ").append(string);
			return;
		}

		if (time != null) {
			sb.append("time: ");
			time.appendAsString(sb, indentLevel + 1);
			return;
		}

		if (partyInformation != null) {
			sb.append("partyInformation: ");
			partyInformation.appendAsString(sb, indentLevel + 1);
			return;
		}

		if (group != null) {
			sb.append("group: ");
			group.appendAsString(sb, indentLevel + 1);
			return;
		}

		if (partyInformations != null) {
			sb.append("partyInformations: ");
			partyInformations.appendAsString(sb, indentLevel + 1);
			return;
		}

		if (strings != null) {
			sb.append("strings: ");
			strings.appendAsString(sb, indentLevel + 1);
			return;
		}

		if (groups != null) {
			sb.append("groups: ");
			groups.appendAsString(sb, indentLevel + 1);
			return;
		}

		if (monetaryUnits != null) {
			sb.append("monetaryUnits: ");
			monetaryUnits.appendAsString(sb, indentLevel + 1);
			return;
		}

		sb.append("<none>");
	}

}

