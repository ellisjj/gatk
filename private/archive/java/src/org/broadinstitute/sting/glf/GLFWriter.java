/*
* By downloading the PROGRAM you agree to the following terms of use:
* 
* BROAD INSTITUTE
* SOFTWARE LICENSE AGREEMENT
* FOR ACADEMIC NON-COMMERCIAL RESEARCH PURPOSES ONLY
* 
* This Agreement is made between the Broad Institute, Inc. with a principal address at 415 Main Street, Cambridge, MA 02142 (“BROAD”) and the LICENSEE and is effective at the date the downloading is completed (“EFFECTIVE DATE”).
* 
* WHEREAS, LICENSEE desires to license the PROGRAM, as defined hereinafter, and BROAD wishes to have this PROGRAM utilized in the public interest, subject only to the royalty-free, nonexclusive, nontransferable license rights of the United States Government pursuant to 48 CFR 52.227-14; and
* WHEREAS, LICENSEE desires to license the PROGRAM and BROAD desires to grant a license on the following terms and conditions.
* NOW, THEREFORE, in consideration of the promises and covenants made herein, the parties hereto agree as follows:
* 
* 1. DEFINITIONS
* 1.1 PROGRAM shall mean copyright in the object code and source code known as GATK3 and related documentation, if any, as they exist on the EFFECTIVE DATE and can be downloaded from http://www.broadinstitute.org/gatk on the EFFECTIVE DATE.
* 
* 2. LICENSE
* 2.1 Grant. Subject to the terms of this Agreement, BROAD hereby grants to LICENSEE, solely for academic non-commercial research purposes, a non-exclusive, non-transferable license to: (a) download, execute and display the PROGRAM and (b) create bug fixes and modify the PROGRAM. LICENSEE hereby automatically grants to BROAD a non-exclusive, royalty-free, irrevocable license to any LICENSEE bug fixes or modifications to the PROGRAM with unlimited rights to sublicense and/or distribute.  LICENSEE agrees to provide any such modifications and bug fixes to BROAD promptly upon their creation.
* The LICENSEE may apply the PROGRAM in a pipeline to data owned by users other than the LICENSEE and provide these users the results of the PROGRAM provided LICENSEE does so for academic non-commercial purposes only. For clarification purposes, academic sponsored research is not a commercial use under the terms of this Agreement.
* 2.2 No Sublicensing or Additional Rights. LICENSEE shall not sublicense or distribute the PROGRAM, in whole or in part, without prior written permission from BROAD. LICENSEE shall ensure that all of its users agree to the terms of this Agreement. LICENSEE further agrees that it shall not put the PROGRAM on a network, server, or other similar technology that may be accessed by anyone other than the LICENSEE and its employees and users who have agreed to the terms of this agreement.
* 2.3 License Limitations. Nothing in this Agreement shall be construed to confer any rights upon LICENSEE by implication, estoppel, or otherwise to any computer software, trademark, intellectual property, or patent rights of BROAD, or of any other entity, except as expressly granted herein. LICENSEE agrees that the PROGRAM, in whole or part, shall not be used for any commercial purpose, including without limitation, as the basis of a commercial software or hardware product or to provide services. LICENSEE further agrees that the PROGRAM shall not be copied or otherwise adapted in order to circumvent the need for obtaining a license for use of the PROGRAM.
* 
* 3. PHONE-HOME FEATURE
* LICENSEE expressly acknowledges that the PROGRAM contains an embedded automatic reporting system (“PHONE-HOME”) which is enabled by default upon download. Unless LICENSEE requests disablement of PHONE-HOME, LICENSEE agrees that BROAD may collect limited information transmitted by PHONE-HOME regarding LICENSEE and its use of the PROGRAM.  Such information shall include LICENSEE’S user identification, version number of the PROGRAM and tools being run, mode of analysis employed, and any error reports generated during run-time.  Collection of such information is used by BROAD solely to monitor usage rates, fulfill reporting requirements to BROAD funding agencies, drive improvements to the PROGRAM, and facilitate adjustments to PROGRAM-related documentation.
* 
* 4. OWNERSHIP OF INTELLECTUAL PROPERTY
* LICENSEE acknowledges that title to the PROGRAM shall remain with BROAD. The PROGRAM is marked with the following BROAD copyright notice and notice of attribution to contributors. LICENSEE shall retain such notice on all copies. LICENSEE agrees to include appropriate attribution if any results obtained from use of the PROGRAM are included in any publication.
* Copyright 2012-2014 Broad Institute, Inc.
* Notice of attribution: The GATK3 program was made available through the generosity of Medical and Population Genetics program at the Broad Institute, Inc.
* LICENSEE shall not use any trademark or trade name of BROAD, or any variation, adaptation, or abbreviation, of such marks or trade names, or any names of officers, faculty, students, employees, or agents of BROAD except as states above for attribution purposes.
* 
* 5. INDEMNIFICATION
* LICENSEE shall indemnify, defend, and hold harmless BROAD, and their respective officers, faculty, students, employees, associated investigators and agents, and their respective successors, heirs and assigns, (Indemnitees), against any liability, damage, loss, or expense (including reasonable attorneys fees and expenses) incurred by or imposed upon any of the Indemnitees in connection with any claims, suits, actions, demands or judgments arising out of any theory of liability (including, without limitation, actions in the form of tort, warranty, or strict liability and regardless of whether such action has any factual basis) pursuant to any right or license granted under this Agreement.
* 
* 6. NO REPRESENTATIONS OR WARRANTIES
* THE PROGRAM IS DELIVERED AS IS. BROAD MAKES NO REPRESENTATIONS OR WARRANTIES OF ANY KIND CONCERNING THE PROGRAM OR THE COPYRIGHT, EXPRESS OR IMPLIED, INCLUDING, WITHOUT LIMITATION, WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, NONINFRINGEMENT, OR THE ABSENCE OF LATENT OR OTHER DEFECTS, WHETHER OR NOT DISCOVERABLE. BROAD EXTENDS NO WARRANTIES OF ANY KIND AS TO PROGRAM CONFORMITY WITH WHATEVER USER MANUALS OR OTHER LITERATURE MAY BE ISSUED FROM TIME TO TIME.
* IN NO EVENT SHALL BROAD OR ITS RESPECTIVE DIRECTORS, OFFICERS, EMPLOYEES, AFFILIATED INVESTIGATORS AND AFFILIATES BE LIABLE FOR INCIDENTAL OR CONSEQUENTIAL DAMAGES OF ANY KIND, INCLUDING, WITHOUT LIMITATION, ECONOMIC DAMAGES OR INJURY TO PROPERTY AND LOST PROFITS, REGARDLESS OF WHETHER BROAD SHALL BE ADVISED, SHALL HAVE OTHER REASON TO KNOW, OR IN FACT SHALL KNOW OF THE POSSIBILITY OF THE FOREGOING.
* 
* 7. ASSIGNMENT
* This Agreement is personal to LICENSEE and any rights or obligations assigned by LICENSEE without the prior written consent of BROAD shall be null and void.
* 
* 8. MISCELLANEOUS
* 8.1 Export Control. LICENSEE gives assurance that it will comply with all United States export control laws and regulations controlling the export of the PROGRAM, including, without limitation, all Export Administration Regulations of the United States Department of Commerce. Among other things, these laws and regulations prohibit, or require a license for, the export of certain types of software to specified countries.
* 8.2 Termination. LICENSEE shall have the right to terminate this Agreement for any reason upon prior written notice to BROAD. If LICENSEE breaches any provision hereunder, and fails to cure such breach within thirty (30) days, BROAD may terminate this Agreement immediately. Upon termination, LICENSEE shall provide BROAD with written assurance that the original and all copies of the PROGRAM have been destroyed, except that, upon prior written authorization from BROAD, LICENSEE may retain a copy for archive purposes.
* 8.3 Survival. The following provisions shall survive the expiration or termination of this Agreement: Articles 1, 3, 4, 5 and Sections 2.2, 2.3, 7.3, and 7.4.
* 8.4 Notice. Any notices under this Agreement shall be in writing, shall specifically refer to this Agreement, and shall be sent by hand, recognized national overnight courier, confirmed facsimile transmission, confirmed electronic mail, or registered or certified mail, postage prepaid, return receipt requested. All notices under this Agreement shall be deemed effective upon receipt.
* 8.5 Amendment and Waiver; Entire Agreement. This Agreement may be amended, supplemented, or otherwise modified only by means of a written instrument signed by all parties. Any waiver of any rights or failure to act in a specific instance shall relate only to such instance and shall not be construed as an agreement to waive any rights or fail to act in any other instance, whether or not similar. This Agreement constitutes the entire agreement among the parties with respect to its subject matter and supersedes prior agreements or understandings between the parties relating to its subject matter.
* 8.6 Binding Effect; Headings. This Agreement shall be binding upon and inure to the benefit of the parties and their respective permitted successors and assigns. All headings are for convenience only and shall not affect the meaning of any provision of this Agreement.
* 8.7 Governing Law. This Agreement shall be construed, governed, interpreted and applied in accordance with the internal laws of the Commonwealth of Massachusetts, U.S.A., without regard to conflict of laws principles.
*/

package org.broadinstitute.sting.utils.genotype.glf;

import net.sf.samtools.SAMSequenceRecord;
import net.sf.samtools.util.BinaryCodec;
import net.sf.samtools.util.BlockCompressedOutputStream;
import org.broad.tribble.util.variantcontext.VariantContext;
import org.broadinstitute.sting.utils.genotype.IndelLikelihood;
import org.broadinstitute.sting.utils.genotype.LikelihoodObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.OutputStream;
/*
 * Copyright (c) 2009 The Broad Institute
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

/**
 * @author aaron
 * @version 1.0
 *          <p/>
 *          This class writes GLF files. You can either specify GLFRecords, or programaticly generate
 *          single and variable length genotype calls using the provided functions.  When you've finished
 *          generating GLF records, make sure you close the file.
 */
// TODO -- DELETE ME GLF
public class GLFWriter {
    // our output codec
    private final BinaryCodec outputBinaryCodec;

    // the glf magic number, which identifies a properly formatted GLF file
    public static final short[] glfMagic = {'G', 'L', 'F', '\3'};

    // our header text, reference sequence name (i.e. chr1), and it's length
    private String headerText = null;
    private String referenceSequenceName = null;
    private long referenceSequenceLength = 0;

    // we need to store the last record so we can calculate the offsets
    private GLFRecord mLastRecord = null;

    // the last position written
    private int lastPos = 1;

    // a field for storing the RMS of the mapping qualities in a mutable variant context
    public static final String RMS_MAPPING_QUAL = "RMS_MAPPING_QUAL";

    /**
     * The public constructor for creating a GLF object
     *
     * @param writeTo    the location to write to
     */
    public GLFWriter(File writeTo) {
        outputBinaryCodec = new BinaryCodec(new DataOutputStream(new BlockCompressedOutputStream(writeTo)));
        outputBinaryCodec.setOutputFileName(writeTo.toString());
    }

    /**
     * The public constructor for creating a GLF object
     *
     * @param writeTo    the location to write to
     */
    public GLFWriter(OutputStream writeTo) {
        outputBinaryCodec = new BinaryCodec(writeTo);
        outputBinaryCodec.setOutputFileName(writeTo.toString());
    }

    /**
     * Write out the header information for the GLF file.  The header contains
     * the magic number, the length of the header text, the text itself, the reference
     * sequence (null terminated) preceeded by it's length, and the the genomic
     * length of the reference sequence.
     *
     * @param headerText    the header text to write
     */
    public void writeHeader(String headerText) {
        this.headerText = headerText;
        for (short aGlfMagic : glfMagic) {
            outputBinaryCodec.writeUByte(aGlfMagic);
        }
        if (!(headerText.equals(""))) {
            outputBinaryCodec.writeString(headerText, true, true);
        } else {
            outputBinaryCodec.writeInt(0);
        }
    }

    /**
     * add a point genotype to the GLF writer
     *
     * @param contig     the name of the contig you're calling in
     * @param refBase    the reference base, as a char
     * @param genomicLoc the location the location on the reference contig
     * @param readDepth  the read depth at the specified postion
     * @param rmsMapQ    the root mean square of the mapping quality
     * @param lhValues   the GenotypeLikelihoods object, representing the genotype likelyhoods
     */
    public void addCall(SAMSequenceRecord contig,
                        int genomicLoc,
                        float rmsMapQ,
                        char refBase,
                        int readDepth,
                        LikelihoodObject lhValues) {
        if ( headerText == null )
            throw new IllegalStateException("The GLF Header must be written before calls can be added");

        // check if we've jumped to a new contig
        checkSequence(contig.getSequenceName(), contig.getSequenceLength());

        GLFSingleCall callGLF = new GLFSingleCall(contig.getSequenceName(),
                                                  refBase,
                                                  genomicLoc,
                                                  readDepth,
                                                  (short) rmsMapQ,
                                                  lhValues.toDoubleArray());
        lastPos = genomicLoc;
        callGLF.write(this.outputBinaryCodec,mLastRecord);
        mLastRecord = callGLF;
    }

    /**
     * Add a genotype, given a variant context
     *
     * @param vc  the variant context representing the call to add
     * @param refBase not used by this writer
     */
    public void add(VariantContext vc, byte refBase) {
        throw new UnsupportedOperationException("We no longer support writing GLF");
    }

    /**
     * add a variable length (indel, deletion, etc) to the genotype writer
     *
     * @param contig        the name of the contig you're calling in
     * @param refBase       the reference base
     * @param genomicLoc    the location on the reference contig
     * @param readDepth     the read depth at the specified postion
     * @param rmsMapQ       the root mean square of the mapping quality
     * @param firstHomZyg   the first homozygous call
     * @param secondHomZyg  the second homozygous call
     * @param hetLikelihood the negitive log likelihood of the heterozygote,  from 0 to 255
     */
    public void addVariableLengthCall(SAMSequenceRecord contig,
                                      int genomicLoc,
                                      float rmsMapQ,
                                      int readDepth,
                                      char refBase,
                                      IndelLikelihood firstHomZyg,
                                      IndelLikelihood secondHomZyg,
                                      byte hetLikelihood) {

        if ( headerText == null )
            throw new IllegalStateException("The GLF Header must be written before calls can be added");

        // check if we've jumped to a new contig
        checkSequence(contig.getSequenceName(), contig.getSequenceLength());

        // normalize the two
        GLFVariableLengthCall call = new GLFVariableLengthCall(contig.getSequenceName(),
                                                         refBase,
                                                         genomicLoc - lastPos,
                                                         readDepth,
                                                         (short) rmsMapQ,
                                                         firstHomZyg.getLikelihood(),
                                                         secondHomZyg.getLikelihood(),
                                                         hetLikelihood,
                                                         firstHomZyg.getLengthOfIndel(),
                                                         firstHomZyg.getIndelSequence(),
                                                         secondHomZyg.getLengthOfIndel(),
                                                         secondHomZyg.getIndelSequence());
        lastPos = genomicLoc;
        call.write(this.outputBinaryCodec,mLastRecord);
        mLastRecord = call;
    }

    /**
     * add a GLF record to the output file
     *
     * @param contigName   the contig name
     * @param contigLength the contig length
     * @param rec          the GLF record to write.
     */
    public void addGLFRecord(String contigName, int contigLength, GLFRecord rec) {
        if ( headerText == null )
            throw new IllegalStateException("The GLF Header must be written before records can be added");

        checkSequence(contigName, contigLength);
        rec.write(this.outputBinaryCodec,mLastRecord);
        mLastRecord = rec;
    }

    /**
     * check to see if we've jumped to a new contig
     *
     * @param sequenceName the name for the sequence
     * @param seqLength    the sequence length
     */
    private void checkSequence(String sequenceName, int seqLength) {
        if ((referenceSequenceName == null) || (!referenceSequenceName.equals(sequenceName))) {
            if (this.referenceSequenceName != null) { // don't write the record the first time
                this.writeEndRecord();
            }
            referenceSequenceName = sequenceName;
            referenceSequenceLength = seqLength;
            lastPos = 1;
            addSequence();
        }
    }


    /** add a sequence definition to the glf */
    private void addSequence() {
        if ( headerText == null )
            throw new IllegalStateException("The GLF Header must be written before sequences can be added");

        outputBinaryCodec.writeString(referenceSequenceName, true, true);
        outputBinaryCodec.writeUInt(referenceSequenceLength);
    }

    /** write end record */
    private void writeEndRecord() {
        if ( headerText == null )
            throw new IllegalStateException("The GLF Header must be written before records can be added");

        outputBinaryCodec.writeUByte((short) 0);
    }


    /**
     * close the file.  You must close the file to ensure any remaining data gets
     * written out.
     */
    public void close() {
        writeEndRecord();
        outputBinaryCodec.close();
    }
}


