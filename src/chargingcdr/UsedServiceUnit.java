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


public class UsedServiceUnit implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

	public byte[] code = null;
	private TariffChangeUsage tariffChangeUsage = null;
	private Unsigned32 timeUnit = null;
	private MonetaryUnits moneyUnit = null;
	private Unsigned64 totalOctetsUnit = null;
	private Unsigned64 uplinkOctetsUnit = null;
	private Unsigned64 downlinkOctetsUnit = null;
	private Unsigned64 serviceSpecificUnit = null;
	
	public UsedServiceUnit() {
	}

	public UsedServiceUnit(byte[] code) {
		this.code = code;
	}

	public void setTariffChangeUsage(TariffChangeUsage tariffChangeUsage) {
		this.tariffChangeUsage = tariffChangeUsage;
	}

	public TariffChangeUsage getTariffChangeUsage() {
		return tariffChangeUsage;
	}

	public void setTimeUnit(Unsigned32 timeUnit) {
		this.timeUnit = timeUnit;
	}

	public Unsigned32 getTimeUnit() {
		return timeUnit;
	}

	public void setMoneyUnit(MonetaryUnits moneyUnit) {
		this.moneyUnit = moneyUnit;
	}

	public MonetaryUnits getMoneyUnit() {
		return moneyUnit;
	}

	public void setTotalOctetsUnit(Unsigned64 totalOctetsUnit) {
		this.totalOctetsUnit = totalOctetsUnit;
	}

	public Unsigned64 getTotalOctetsUnit() {
		return totalOctetsUnit;
	}

	public void setUplinkOctetsUnit(Unsigned64 uplinkOctetsUnit) {
		this.uplinkOctetsUnit = uplinkOctetsUnit;
	}

	public Unsigned64 getUplinkOctetsUnit() {
		return uplinkOctetsUnit;
	}

	public void setDownlinkOctetsUnit(Unsigned64 downlinkOctetsUnit) {
		this.downlinkOctetsUnit = downlinkOctetsUnit;
	}

	public Unsigned64 getDownlinkOctetsUnit() {
		return downlinkOctetsUnit;
	}

	public void setServiceSpecificUnit(Unsigned64 serviceSpecificUnit) {
		this.serviceSpecificUnit = serviceSpecificUnit;
	}

	public Unsigned64 getServiceSpecificUnit() {
		return serviceSpecificUnit;
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
		if (serviceSpecificUnit != null) {
			codeLength += serviceSpecificUnit.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 6
			os.write(0x86);
			codeLength += 1;
		}
		
		if (downlinkOctetsUnit != null) {
			codeLength += downlinkOctetsUnit.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 5
			os.write(0x85);
			codeLength += 1;
		}
		
		if (uplinkOctetsUnit != null) {
			codeLength += uplinkOctetsUnit.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 4
			os.write(0x84);
			codeLength += 1;
		}
		
		if (totalOctetsUnit != null) {
			codeLength += totalOctetsUnit.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 3
			os.write(0x83);
			codeLength += 1;
		}
		
		if (moneyUnit != null) {
			codeLength += moneyUnit.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 2
			os.write(0xA2);
			codeLength += 1;
		}
		
		if (timeUnit != null) {
			codeLength += timeUnit.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 1
			os.write(0x81);
			codeLength += 1;
		}
		
		if (tariffChangeUsage != null) {
			codeLength += tariffChangeUsage.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 0
			os.write(0x80);
			codeLength += 1;
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
		BerTag berTag = new BerTag();

		if (withTag) {
			codeLength += tag.decodeAndCheck(is);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(is);

		int totalLength = length.val;
		codeLength += totalLength;

		if (totalLength == 0) {
			return codeLength;
		}
		subCodeLength += berTag.decode(is);
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
			tariffChangeUsage = new TariffChangeUsage();
			subCodeLength += tariffChangeUsage.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
			timeUnit = new Unsigned32();
			subCodeLength += timeUnit.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 2)) {
			moneyUnit = new MonetaryUnits();
			subCodeLength += moneyUnit.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 3)) {
			totalOctetsUnit = new Unsigned64();
			subCodeLength += totalOctetsUnit.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 4)) {
			uplinkOctetsUnit = new Unsigned64();
			subCodeLength += uplinkOctetsUnit.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 5)) {
			downlinkOctetsUnit = new Unsigned64();
			subCodeLength += downlinkOctetsUnit.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 6)) {
			serviceSpecificUnit = new Unsigned64();
			subCodeLength += serviceSpecificUnit.decode(is, false);
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
		boolean firstSelectedElement = true;
		if (tariffChangeUsage != null) {
			sb.append("\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("tariffChangeUsage: ").append(tariffChangeUsage);
			firstSelectedElement = false;
		}
		
		if (timeUnit != null) {
			if (!firstSelectedElement) {
				sb.append(",\n");
			}
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("timeUnit: ").append(timeUnit);
			firstSelectedElement = false;
		}
		
		if (moneyUnit != null) {
			if (!firstSelectedElement) {
				sb.append(",\n");
			}
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("moneyUnit: ");
			moneyUnit.appendAsString(sb, indentLevel + 1);
			firstSelectedElement = false;
		}
		
		if (totalOctetsUnit != null) {
			if (!firstSelectedElement) {
				sb.append(",\n");
			}
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("totalOctetsUnit: ").append(totalOctetsUnit);
			firstSelectedElement = false;
		}
		
		if (uplinkOctetsUnit != null) {
			if (!firstSelectedElement) {
				sb.append(",\n");
			}
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("uplinkOctetsUnit: ").append(uplinkOctetsUnit);
			firstSelectedElement = false;
		}
		
		if (downlinkOctetsUnit != null) {
			if (!firstSelectedElement) {
				sb.append(",\n");
			}
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("downlinkOctetsUnit: ").append(downlinkOctetsUnit);
			firstSelectedElement = false;
		}
		
		if (serviceSpecificUnit != null) {
			if (!firstSelectedElement) {
				sb.append(",\n");
			}
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("serviceSpecificUnit: ").append(serviceSpecificUnit);
			firstSelectedElement = false;
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}

