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


public class SelectionTreeParameter implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

	public byte[] code = null;
	private BerUTF8String selectionTreeID = null;
	private BerUTF8String selectionTreeVersion = null;
	private SelectionTreeType selectionTreeType = null;
	
	public SelectionTreeParameter() {
	}

	public SelectionTreeParameter(byte[] code) {
		this.code = code;
	}

	public void setSelectionTreeID(BerUTF8String selectionTreeID) {
		this.selectionTreeID = selectionTreeID;
	}

	public BerUTF8String getSelectionTreeID() {
		return selectionTreeID;
	}

	public void setSelectionTreeVersion(BerUTF8String selectionTreeVersion) {
		this.selectionTreeVersion = selectionTreeVersion;
	}

	public BerUTF8String getSelectionTreeVersion() {
		return selectionTreeVersion;
	}

	public void setSelectionTreeType(SelectionTreeType selectionTreeType) {
		this.selectionTreeType = selectionTreeType;
	}

	public SelectionTreeType getSelectionTreeType() {
		return selectionTreeType;
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
		codeLength += selectionTreeType.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 2
		os.write(0x82);
		codeLength += 1;
		
		codeLength += selectionTreeVersion.encode(os, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 1
		os.write(0x81);
		codeLength += 1;
		
		codeLength += selectionTreeID.encode(os, false);
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
			selectionTreeID = new BerUTF8String();
			subCodeLength += selectionTreeID.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
			selectionTreeVersion = new BerUTF8String();
			subCodeLength += selectionTreeVersion.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 2)) {
			selectionTreeType = new SelectionTreeType();
			subCodeLength += selectionTreeType.decode(is, false);
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
		if (selectionTreeID != null) {
			sb.append("selectionTreeID: ").append(selectionTreeID);
		}
		else {
			sb.append("selectionTreeID: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (selectionTreeVersion != null) {
			sb.append("selectionTreeVersion: ").append(selectionTreeVersion);
		}
		else {
			sb.append("selectionTreeVersion: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (selectionTreeType != null) {
			sb.append("selectionTreeType: ").append(selectionTreeType);
		}
		else {
			sb.append("selectionTreeType: <empty-required-field>");
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}

