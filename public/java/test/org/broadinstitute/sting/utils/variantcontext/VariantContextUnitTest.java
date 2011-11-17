// our package
package org.broadinstitute.sting.utils.variantcontext;


// the imports for unit testing.


import org.broadinstitute.sting.BaseTest;
import org.broadinstitute.sting.utils.codecs.vcf.VCFConstants;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.util.*;


public class VariantContextUnitTest extends BaseTest {
    Allele A, Aref, C, T, Tref;
    Allele del, delRef, ATC, ATCref;

    // A [ref] / T at 10
    String snpLoc = "chr1";
    int snpLocStart = 10;
    int snpLocStop = 10;

    // - / ATC [ref] from 20-23
    String delLoc = "chr1";
    int delLocStart = 20;
    int delLocStop = 23;

    // - [ref] / ATC from 20-20
    String insLoc = "chr1";
    int insLocStart = 20;
    int insLocStop = 20;

    // - / A / T / ATC [ref] from 20-23
    String mixedLoc = "chr1";
    int mixedLocStart = 20;
    int mixedLocStop = 23;

    @BeforeSuite
    public void before() {
        del = Allele.create("-");
        delRef = Allele.create("-", true);

        A = Allele.create("A");
        C = Allele.create("C");
        Aref = Allele.create("A", true);
        T = Allele.create("T");
        Tref = Allele.create("T", true);

        ATC = Allele.create("ATC");
        ATCref = Allele.create("ATC", true);
    }

    @Test
    public void testDetermineTypes() {
        Allele ACref = Allele.create("AC", true);
        Allele AC = Allele.create("AC");
        Allele AT = Allele.create("AT");
        Allele C = Allele.create("C");
        Allele CAT = Allele.create("CAT");
        Allele TAref = Allele.create("TA", true);
        Allele TA = Allele.create("TA");
        Allele TC = Allele.create("TC");
        Allele symbolic = Allele.create("<FOO>");

        // test REF
        List<Allele> alleles = Arrays.asList(Tref);
        VariantContext vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles);
        Assert.assertEquals(vc.getType(), VariantContext.Type.NO_VARIATION);

        // test SNPs
        alleles = Arrays.asList(Tref, A);
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles);
        Assert.assertEquals(vc.getType(), VariantContext.Type.SNP);

        alleles = Arrays.asList(Tref, A, C);
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles);
        Assert.assertEquals(vc.getType(), VariantContext.Type.SNP);

        // test MNPs
        alleles = Arrays.asList(ACref, TA);
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop+1, alleles);
        Assert.assertEquals(vc.getType(), VariantContext.Type.MNP);

        alleles = Arrays.asList(ATCref, CAT, Allele.create("GGG"));
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop+2, alleles);
        Assert.assertEquals(vc.getType(), VariantContext.Type.MNP);

        // test INDELs
        alleles = Arrays.asList(Aref, ATC);
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles, null, CommonInfo.NO_NEG_LOG_10PERROR, null, null, (byte)'A');
        Assert.assertEquals(vc.getType(), VariantContext.Type.INDEL);

        alleles = Arrays.asList(ATCref, A);
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop+2, alleles, null, CommonInfo.NO_NEG_LOG_10PERROR, null, null, (byte)'A');
        Assert.assertEquals(vc.getType(), VariantContext.Type.INDEL);

        alleles = Arrays.asList(Tref, TA, TC);
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles, null, CommonInfo.NO_NEG_LOG_10PERROR, null, null, (byte)'A');
        Assert.assertEquals(vc.getType(), VariantContext.Type.INDEL);

        alleles = Arrays.asList(ATCref, A, AC);
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop+2, alleles, null, CommonInfo.NO_NEG_LOG_10PERROR, null, null, (byte)'A');
        Assert.assertEquals(vc.getType(), VariantContext.Type.INDEL);

        alleles = Arrays.asList(ATCref, A, Allele.create("ATCTC"));
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop+2, alleles, null, CommonInfo.NO_NEG_LOG_10PERROR, null, null, (byte)'A');
        Assert.assertEquals(vc.getType(), VariantContext.Type.INDEL);

        // test MIXED
        alleles = Arrays.asList(TAref, T, TC);
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop+1, alleles, null, CommonInfo.NO_NEG_LOG_10PERROR, null, null, (byte)'A');
        Assert.assertEquals(vc.getType(), VariantContext.Type.MIXED);

        alleles = Arrays.asList(TAref, T, AC);
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop+1, alleles, null, CommonInfo.NO_NEG_LOG_10PERROR, null, null, (byte)'A');
        Assert.assertEquals(vc.getType(), VariantContext.Type.MIXED);

        alleles = Arrays.asList(ACref, ATC, AT);
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop+1, alleles, null, CommonInfo.NO_NEG_LOG_10PERROR, null, null, (byte)'A');
        Assert.assertEquals(vc.getType(), VariantContext.Type.MIXED);

        alleles = Arrays.asList(Aref, T, symbolic);
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles, null, CommonInfo.NO_NEG_LOG_10PERROR, null, null, (byte)'A');
        Assert.assertEquals(vc.getType(), VariantContext.Type.MIXED);

        // test SYMBOLIC
        alleles = Arrays.asList(Tref, symbolic);
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles, null, CommonInfo.NO_NEG_LOG_10PERROR, null, null, (byte)'A');
        Assert.assertEquals(vc.getType(), VariantContext.Type.SYMBOLIC);
    }

    @Test
    public void testMultipleSNPAlleleOrdering() {
        final List<Allele> allelesNaturalOrder = Arrays.asList(Aref, C, T);
        final List<Allele> allelesUnnaturalOrder = Arrays.asList(Aref, T, C);
        VariantContext naturalVC = new VariantContext("natural", VCFConstants.EMPTY_ID_FIELD, snpLoc, snpLocStart, snpLocStop, allelesNaturalOrder);
        VariantContext unnaturalVC = new VariantContext("unnatural", VCFConstants.EMPTY_ID_FIELD, snpLoc, snpLocStart, snpLocStop, allelesUnnaturalOrder);
        Assert.assertEquals(new ArrayList<Allele>(naturalVC.getAlleles()), allelesNaturalOrder);
        Assert.assertEquals(new ArrayList<Allele>(unnaturalVC.getAlleles()), allelesUnnaturalOrder);
    }

    @Test
    public void testCreatingSNPVariantContext() {

        List<Allele> alleles = Arrays.asList(Aref, T);
        VariantContext vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles);

        Assert.assertEquals(vc.getChr(), snpLoc);
        Assert.assertEquals(vc.getStart(), snpLocStart);
        Assert.assertEquals(vc.getEnd(), snpLocStop);
        Assert.assertEquals(vc.getType(), VariantContext.Type.SNP);
        Assert.assertTrue(vc.isSNP());
        Assert.assertFalse(vc.isIndel());
        Assert.assertFalse(vc.isSimpleInsertion());
        Assert.assertFalse(vc.isSimpleDeletion());
        Assert.assertFalse(vc.isMixed());
        Assert.assertTrue(vc.isBiallelic());
        Assert.assertEquals(vc.getNAlleles(), 2);

        Assert.assertEquals(vc.getReference(), Aref);
        Assert.assertEquals(vc.getAlleles().size(), 2);
        Assert.assertEquals(vc.getAlternateAlleles().size(), 1);
        Assert.assertEquals(vc.getAlternateAllele(0), T);

        Assert.assertFalse(vc.hasGenotypes());

        Assert.assertEquals(vc.getSampleNames().size(), 0);
    }

    @Test
    public void testCreatingRefVariantContext() {
        List<Allele> alleles = Arrays.asList(Aref);
        VariantContext vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles);

        Assert.assertEquals(vc.getChr(), snpLoc);
        Assert.assertEquals(vc.getStart(), snpLocStart);
        Assert.assertEquals(vc.getEnd(), snpLocStop);
        Assert.assertEquals(VariantContext.Type.NO_VARIATION, vc.getType());
        Assert.assertFalse(vc.isSNP());
        Assert.assertFalse(vc.isIndel());
        Assert.assertFalse(vc.isSimpleInsertion());
        Assert.assertFalse(vc.isSimpleDeletion());
        Assert.assertFalse(vc.isMixed());
        Assert.assertFalse(vc.isBiallelic());
        Assert.assertEquals(vc.getNAlleles(), 1);

        Assert.assertEquals(vc.getReference(), Aref);
        Assert.assertEquals(vc.getAlleles().size(), 1);
        Assert.assertEquals(vc.getAlternateAlleles().size(), 0);
        //Assert.assertEquals(vc.getAlternateAllele(0), T);

        Assert.assertFalse(vc.hasGenotypes());
        Assert.assertEquals(vc.getSampleNames().size(), 0);
    }

    @Test
    public void testCreatingDeletionVariantContext() {
        List<Allele> alleles = Arrays.asList(ATCref, del);
        VariantContext vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, delLoc, delLocStart, delLocStop, alleles, null, CommonInfo.NO_NEG_LOG_10PERROR, null, null, (byte)'A');

        Assert.assertEquals(vc.getChr(), delLoc);
        Assert.assertEquals(vc.getStart(), delLocStart);
        Assert.assertEquals(vc.getEnd(), delLocStop);
        Assert.assertEquals(vc.getType(), VariantContext.Type.INDEL);
        Assert.assertFalse(vc.isSNP());
        Assert.assertTrue(vc.isIndel());
        Assert.assertFalse(vc.isSimpleInsertion());
        Assert.assertTrue(vc.isSimpleDeletion());
        Assert.assertFalse(vc.isMixed());
        Assert.assertTrue(vc.isBiallelic());
        Assert.assertEquals(vc.getNAlleles(), 2);

        Assert.assertEquals(vc.getReference(), ATCref);
        Assert.assertEquals(vc.getAlleles().size(), 2);
        Assert.assertEquals(vc.getAlternateAlleles().size(), 1);
        Assert.assertEquals(vc.getAlternateAllele(0), del);

        Assert.assertFalse(vc.hasGenotypes());

        Assert.assertEquals(vc.getSampleNames().size(), 0);
    }

    @Test
    public void testCreatingInsertionVariantContext() {
        List<Allele> alleles = Arrays.asList(delRef, ATC);
        VariantContext vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, insLoc, insLocStart, insLocStop, alleles, null, CommonInfo.NO_NEG_LOG_10PERROR, null, null, (byte)'A');

        Assert.assertEquals(vc.getChr(), insLoc);
        Assert.assertEquals(vc.getStart(), insLocStart);
        Assert.assertEquals(vc.getEnd(), insLocStop);
        Assert.assertEquals(vc.getType(), VariantContext.Type.INDEL);
        Assert.assertFalse(vc.isSNP());
        Assert.assertTrue(vc.isIndel());
        Assert.assertTrue(vc.isSimpleInsertion());
        Assert.assertFalse(vc.isSimpleDeletion());
        Assert.assertFalse(vc.isMixed());
        Assert.assertTrue(vc.isBiallelic());
        Assert.assertEquals(vc.getNAlleles(), 2);

        Assert.assertEquals(vc.getReference(), delRef);
        Assert.assertEquals(vc.getAlleles().size(), 2);
        Assert.assertEquals(vc.getAlternateAlleles().size(), 1);
        Assert.assertEquals(vc.getAlternateAllele(0), ATC);
        Assert.assertFalse(vc.hasGenotypes());

        Assert.assertEquals(vc.getSampleNames().size(), 0);
    }

    @Test
    public void testCreatingPartiallyCalledGenotype() {
        List<Allele> alleles = Arrays.asList(Aref, C);
        Genotype g = new Genotype("foo", Arrays.asList(C, Allele.NO_CALL), 10);
        VariantContext vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc, snpLocStart, snpLocStop, alleles, Arrays.asList(g));

        Assert.assertTrue(vc.isSNP());
        Assert.assertEquals(vc.getNAlleles(), 2);
        Assert.assertTrue(vc.hasGenotypes());
        Assert.assertFalse(vc.isMonomorphic());
        Assert.assertTrue(vc.isPolymorphic());
        Assert.assertEquals(vc.getGenotype("foo"), g);
        Assert.assertEquals(vc.getChromosomeCount(), 2); // we know that there are 2 chromosomes, even though one isn't called
        Assert.assertEquals(vc.getChromosomeCount(Aref), 0);
        Assert.assertEquals(vc.getChromosomeCount(C), 1);
        Assert.assertFalse(vc.getGenotype("foo").isHet());
        Assert.assertFalse(vc.getGenotype("foo").isHom());
        Assert.assertFalse(vc.getGenotype("foo").isNoCall());
        Assert.assertFalse(vc.getGenotype("foo").isHom());
        Assert.assertTrue(vc.getGenotype("foo").isMixed());
        Assert.assertEquals(vc.getGenotype("foo").getType(), Genotype.Type.MIXED);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testBadConstructorArgs1() {
        new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, insLoc, insLocStart, insLocStop, Arrays.asList(delRef, ATCref));
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testBadConstructorArgs2() {
        new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, insLoc, insLocStart, insLocStop, Arrays.asList(delRef, del));
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testBadConstructorArgs3() {
        new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, insLoc, insLocStart, insLocStop, Arrays.asList(del));
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testBadConstructorArgs4() {
        new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, insLoc, insLocStart, insLocStop, Collections.<Allele>emptyList());
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testBadConstructorArgsDuplicateAlleles1() {
        new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, insLoc, insLocStart, insLocStop, Arrays.asList(Aref, T, T));
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testBadConstructorArgsDuplicateAlleles2() {
        new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, insLoc, insLocStart, insLocStop, Arrays.asList(Aref, A));
    }

    @Test (expectedExceptions = IllegalStateException.class)
    public void testBadLoc1() {
        List<Allele> alleles = Arrays.asList(Aref, T, del);
        new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, delLoc, delLocStart, delLocStop, alleles);
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testBadID1() {
        new VariantContext("test", null, delLoc, delLocStart, delLocStop, Arrays.asList(Aref, T));
    }

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testBadID2() {
        new VariantContext("test", "", delLoc, delLocStart, delLocStop, Arrays.asList(Aref, T));
    }

    @Test
    public void testAccessingSimpleSNPGenotypes() {
        List<Allele> alleles = Arrays.asList(Aref, T);

        Genotype g1 = new Genotype("AA", Arrays.asList(Aref, Aref), 10);
        Genotype g2 = new Genotype("AT", Arrays.asList(Aref, T), 10);
        Genotype g3 = new Genotype("TT", Arrays.asList(T, T), 10);

        VariantContext vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles, Arrays.asList(g1, g2, g3));

        Assert.assertTrue(vc.hasGenotypes());
        Assert.assertFalse(vc.isMonomorphic());
        Assert.assertTrue(vc.isPolymorphic());
        Assert.assertEquals(vc.getSampleNames().size(), 3);

        Assert.assertEquals(vc.getGenotypes().size(), 3);
        Assert.assertEquals(vc.getGenotypes().get("AA"), g1);
        Assert.assertEquals(vc.getGenotype("AA"), g1);
        Assert.assertEquals(vc.getGenotypes().get("AT"), g2);
        Assert.assertEquals(vc.getGenotype("AT"), g2);
        Assert.assertEquals(vc.getGenotypes().get("TT"), g3);
        Assert.assertEquals(vc.getGenotype("TT"), g3);

        Assert.assertTrue(vc.hasGenotype("AA"));
        Assert.assertTrue(vc.hasGenotype("AT"));
        Assert.assertTrue(vc.hasGenotype("TT"));
        Assert.assertFalse(vc.hasGenotype("foo"));
        Assert.assertFalse(vc.hasGenotype("TTT"));
        Assert.assertFalse(vc.hasGenotype("at"));
        Assert.assertFalse(vc.hasGenotype("tt"));

        Assert.assertEquals(vc.getChromosomeCount(), 6);
        Assert.assertEquals(vc.getChromosomeCount(Aref), 3);
        Assert.assertEquals(vc.getChromosomeCount(T), 3);
    }

    @Test
    public void testAccessingCompleteGenotypes() {
        List<Allele> alleles = Arrays.asList(Aref, T, del);

        Genotype g1 = new Genotype("AA", Arrays.asList(Aref, Aref), 10);
        Genotype g2 = new Genotype("AT", Arrays.asList(Aref, T), 10);
        Genotype g3 = new Genotype("TT", Arrays.asList(T, T), 10);
        Genotype g4 = new Genotype("Td", Arrays.asList(T, del), 10);
        Genotype g5 = new Genotype("dd", Arrays.asList(del, del), 10);
        Genotype g6 = new Genotype("..", Arrays.asList(Allele.NO_CALL, Allele.NO_CALL), 10);

        VariantContext vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles, Arrays.asList(g1, g2, g3, g4, g5, g6));

        Assert.assertTrue(vc.hasGenotypes());
        Assert.assertFalse(vc.isMonomorphic());
        Assert.assertTrue(vc.isPolymorphic());
        Assert.assertEquals(vc.getGenotypes().size(), 6);

        Assert.assertEquals(3, vc.getGenotypes(Arrays.asList("AA", "Td", "dd")).size());

        Assert.assertEquals(10, vc.getChromosomeCount());
        Assert.assertEquals(3, vc.getChromosomeCount(Aref));
        Assert.assertEquals(4, vc.getChromosomeCount(T));
        Assert.assertEquals(3, vc.getChromosomeCount(del));
        Assert.assertEquals(2, vc.getChromosomeCount(Allele.NO_CALL));
    }

    @Test
    public void testAccessingRefGenotypes() {
        List<Allele> alleles1 = Arrays.asList(Aref, T);
        List<Allele> alleles2 = Arrays.asList(Aref);
        List<Allele> alleles3 = Arrays.asList(Aref, T, del);
        for ( List<Allele> alleles : Arrays.asList(alleles1, alleles2, alleles3)) {
            Genotype g1 = new Genotype("AA1", Arrays.asList(Aref, Aref), 10);
            Genotype g2 = new Genotype("AA2", Arrays.asList(Aref, Aref), 10);
            Genotype g3 = new Genotype("..", Arrays.asList(Allele.NO_CALL, Allele.NO_CALL), 10);
            VariantContext vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles, Arrays.asList(g1, g2, g3));

            Assert.assertTrue(vc.hasGenotypes());
            Assert.assertTrue(vc.isMonomorphic());
            Assert.assertFalse(vc.isPolymorphic());
            Assert.assertEquals(vc.getGenotypes().size(), 3);

            Assert.assertEquals(4, vc.getChromosomeCount());
            Assert.assertEquals(4, vc.getChromosomeCount(Aref));
            Assert.assertEquals(0, vc.getChromosomeCount(T));
            Assert.assertEquals(2, vc.getChromosomeCount(Allele.NO_CALL));
        }
    }

    @Test
    public void testFilters() {
        List<Allele> alleles = Arrays.asList(Aref, T, del);
        Genotype g1 = new Genotype("AA", Arrays.asList(Aref, Aref), 10);
        Genotype g2 = new Genotype("AT", Arrays.asList(Aref, T), 10);

        VariantContext vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles, Arrays.asList(g1,g2));

        Assert.assertTrue(vc.isNotFiltered());
        Assert.assertFalse(vc.isFiltered());
        Assert.assertEquals(0, vc.getFilters().size());

        Set<String> filters = new HashSet<String>(Arrays.asList("BAD_SNP_BAD!"));
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles, Arrays.asList(g1,g2), VariantContext.NO_NEG_LOG_10PERROR, filters, null);

        Assert.assertFalse(vc.isNotFiltered());
        Assert.assertTrue(vc.isFiltered());
        Assert.assertEquals(1, vc.getFilters().size());

        filters = new HashSet<String>(Arrays.asList("BAD_SNP_BAD!", "REALLY_BAD_SNP", "CHRIST_THIS_IS_TERRIBLE"));
        vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop, alleles, Arrays.asList(g1,g2), VariantContext.NO_NEG_LOG_10PERROR, filters, null);

        Assert.assertFalse(vc.isNotFiltered());
        Assert.assertTrue(vc.isFiltered());
        Assert.assertEquals(3, vc.getFilters().size());
    }

    @Test
    public void testVCFfromGenotypes() {
        List<Allele> alleles = Arrays.asList(Aref, T, del);
        Genotype g1 = new Genotype("AA", Arrays.asList(Aref, Aref), 10);
        Genotype g2 = new Genotype("AT", Arrays.asList(Aref, T), 10);
        Genotype g3 = new Genotype("TT", Arrays.asList(T, T), 10);
        Genotype g4 = new Genotype("..", Arrays.asList(Allele.NO_CALL, Allele.NO_CALL), 10);
        Genotype g5 = new Genotype("--", Arrays.asList(del, del), 10);
        VariantContext vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc,snpLocStart, snpLocStop , alleles, Arrays.asList(g1,g2,g3,g4,g5));

        VariantContext vc12 = vc.subContextFromSamples(new HashSet<String>(Arrays.asList(g1.getSampleName(), g2.getSampleName())));
        VariantContext vc1 = vc.subContextFromSamples(new HashSet<String>(Arrays.asList(g1.getSampleName())));
        VariantContext vc23 = vc.subContextFromSamples(new HashSet<String>(Arrays.asList(g2.getSampleName(), g3.getSampleName())));
        VariantContext vc4 = vc.subContextFromSamples(new HashSet<String>(Arrays.asList(g4.getSampleName())));
        VariantContext vc14 = vc.subContextFromSamples(new HashSet<String>(Arrays.asList(g1.getSampleName(), g4.getSampleName())));
        VariantContext vc5 = vc.subContextFromSamples(new HashSet<String>(Arrays.asList(g5.getSampleName())));

        Assert.assertTrue(vc12.isPolymorphic());
        Assert.assertTrue(vc23.isPolymorphic());
        Assert.assertTrue(vc1.isMonomorphic());
        Assert.assertTrue(vc4.isMonomorphic());
        Assert.assertTrue(vc14.isMonomorphic());
        Assert.assertTrue(vc5.isPolymorphic());

        Assert.assertTrue(vc12.isSNP());
        Assert.assertTrue(vc12.isVariant());
        Assert.assertTrue(vc12.isBiallelic());

        Assert.assertFalse(vc1.isSNP());
        Assert.assertFalse(vc1.isVariant());
        Assert.assertFalse(vc1.isBiallelic());

        Assert.assertTrue(vc23.isSNP());
        Assert.assertTrue(vc23.isVariant());
        Assert.assertTrue(vc23.isBiallelic());

        Assert.assertFalse(vc4.isSNP());
        Assert.assertFalse(vc4.isVariant());
        Assert.assertFalse(vc4.isBiallelic());

        Assert.assertFalse(vc14.isSNP());
        Assert.assertFalse(vc14.isVariant());
        Assert.assertFalse(vc14.isBiallelic());

        Assert.assertTrue(vc5.isIndel());
        Assert.assertTrue(vc5.isSimpleDeletion());
        Assert.assertTrue(vc5.isVariant());
        Assert.assertTrue(vc5.isBiallelic());

        Assert.assertEquals(3, vc12.getChromosomeCount(Aref));
        Assert.assertEquals(1, vc23.getChromosomeCount(Aref));
        Assert.assertEquals(2, vc1.getChromosomeCount(Aref));
        Assert.assertEquals(0, vc4.getChromosomeCount(Aref));
        Assert.assertEquals(2, vc14.getChromosomeCount(Aref));
        Assert.assertEquals(0, vc5.getChromosomeCount(Aref));
    }

    public void testGetGenotypeMethods() {
        Genotype g1 = new Genotype("AA", Arrays.asList(Aref, Aref), 10);
        Genotype g2 = new Genotype("AT", Arrays.asList(Aref, T), 10);
        Genotype g3 = new Genotype("TT", Arrays.asList(T, T), 10);
        GenotypesContext gc = GenotypesContext.create(g1, g2, g3);
        VariantContext vc = new VariantContext("genotypes", VCFConstants.EMPTY_ID_FIELD, snpLoc, snpLocStart, snpLocStop, Arrays.asList(Aref, T), gc);

        Assert.assertEquals(vc.getGenotype("AA"), g1);
        Assert.assertEquals(vc.getGenotype("AT"), g2);
        Assert.assertEquals(vc.getGenotype("TT"), g3);
        Assert.assertEquals(vc.getGenotype("CC"), null);

        Assert.assertEquals(vc.getGenotypes(), gc);
        Assert.assertEquals(vc.getGenotypes(Arrays.asList("AA", "AT")), Arrays.asList(g1, g2));
        Assert.assertEquals(vc.getGenotypes(Arrays.asList("AA", "TT")), Arrays.asList(g1, g3));
        Assert.assertEquals(vc.getGenotypes(Arrays.asList("AA", "AT", "TT")), Arrays.asList(g1, g2, g3));
        Assert.assertEquals(vc.getGenotypes(Arrays.asList("AA", "AT", "CC")), Arrays.asList(g1, g2));

        Assert.assertEquals(vc.getGenotype(0), g1);
        Assert.assertEquals(vc.getGenotype(1), g2);
        Assert.assertEquals(vc.getGenotype(2), g3);
    }

    // --------------------------------------------------------------------------------
    //
    // Test allele merging
    //
    // --------------------------------------------------------------------------------

    private class GetAllelesTest extends TestDataProvider {
        List<Allele> alleles;

        private GetAllelesTest(String name, Allele... arg) {
            super(GetAllelesTest.class, name);
            this.alleles = Arrays.asList(arg);
        }

        public String toString() {
            return String.format("%s input=%s", super.toString(), alleles);
        }
    }

    @DataProvider(name = "getAlleles")
    public Object[][] mergeAllelesData() {
        new GetAllelesTest("A*",   Aref);
        new GetAllelesTest("-*",   delRef);
        new GetAllelesTest("A*/C", Aref, C);
        new GetAllelesTest("A*/C/T", Aref, C, T);
        new GetAllelesTest("A*/T/C", Aref, T, C);
        new GetAllelesTest("A*/C/T/-", Aref, C, T, del);
        new GetAllelesTest("A*/T/C/-", Aref, T, C, del);
        new GetAllelesTest("A*/-/T/C", Aref, del, T, C);

        return GetAllelesTest.getTests(GetAllelesTest.class);
    }

    @Test(dataProvider = "getAlleles")
    public void testMergeAlleles(GetAllelesTest cfg) {
        final List<Allele> altAlleles = cfg.alleles.subList(1, cfg.alleles.size());
        final VariantContext vc = new VariantContext("test", VCFConstants.EMPTY_ID_FIELD, snpLoc, snpLocStart, snpLocStop, cfg.alleles, null, CommonInfo.NO_NEG_LOG_10PERROR, null, null, (byte)'A');

        Assert.assertEquals(vc.getAlleles(), cfg.alleles, "VC alleles not the same as input alleles");
        Assert.assertEquals(vc.getNAlleles(), cfg.alleles.size(), "VC getNAlleles not the same as input alleles size");
        Assert.assertEquals(vc.getAlternateAlleles(), altAlleles, "VC alt alleles not the same as input alt alleles");


        for ( int i = 0; i < cfg.alleles.size(); i++ ) {
            final Allele inputAllele = cfg.alleles.get(i);

            Assert.assertTrue(vc.hasAllele(inputAllele));
            if ( inputAllele.isReference() ) {
                final Allele nonRefVersion = Allele.create(inputAllele.getBases(), false);
                Assert.assertTrue(vc.hasAllele(nonRefVersion, true));
                Assert.assertFalse(vc.hasAllele(nonRefVersion, false));
            }

            Assert.assertEquals(inputAllele, vc.getAllele(inputAllele.getBaseString()));
            Assert.assertEquals(inputAllele, vc.getAllele(inputAllele.getBases()));

            if ( i > 0 ) { // it's an alt allele
                Assert.assertEquals(inputAllele, vc.getAlternateAllele(i-1));
            }
        }

        final Allele missingAllele = Allele.create("AACCGGTT"); // does not exist
        Assert.assertNull(vc.getAllele(missingAllele.getBases()));
        Assert.assertFalse(vc.hasAllele(missingAllele));
        Assert.assertFalse(vc.hasAllele(missingAllele, true));
    }

    private class SitesAndGenotypesVC extends TestDataProvider {
        VariantContext vc, copy;

        private SitesAndGenotypesVC(String name, VariantContext original) {
            super(SitesAndGenotypesVC.class, name);
            this.vc = original;
            this.copy = new VariantContext(original);
        }

        public String toString() {
            return String.format("%s input=%s", super.toString(), vc);
        }
    }

    @DataProvider(name = "SitesAndGenotypesVC")
    public Object[][] MakeSitesAndGenotypesVCs() {
        Genotype g1 = new Genotype("AA", Arrays.asList(Aref, Aref), 10);
        Genotype g2 = new Genotype("AT", Arrays.asList(Aref, T), 10);
        Genotype g3 = new Genotype("TT", Arrays.asList(T, T), 10);

        VariantContext sites = new VariantContext("sites", VCFConstants.EMPTY_ID_FIELD, snpLoc, snpLocStart, snpLocStop, Arrays.asList(Aref, T));
        VariantContext genotypes = new VariantContext("genotypes", VCFConstants.EMPTY_ID_FIELD, snpLoc, snpLocStart, snpLocStop, Arrays.asList(Aref, T), Arrays.asList(g1, g2, g3));

        new SitesAndGenotypesVC("sites", sites);
        new SitesAndGenotypesVC("genotypes", genotypes);

        return SitesAndGenotypesVC.getTests(SitesAndGenotypesVC.class);
    }

    // --------------------------------------------------------------------------------
    //
    // Test modifying routines
    //
    // --------------------------------------------------------------------------------
    @Test(dataProvider = "SitesAndGenotypesVC")
    public void runModifyVCTests(SitesAndGenotypesVC cfg) {
        VariantContext modified = VariantContext.modifyLocation(cfg.vc, "chr2", 123, 123);
        Assert.assertEquals(modified.getChr(), "chr2");
        Assert.assertEquals(modified.getStart(), 123);
        Assert.assertEquals(modified.getEnd(), 123);

        modified = VariantContext.modifyID(cfg.vc, "newID");
        Assert.assertEquals(modified.getID(), "newID");

        Set<String> newFilters = Collections.singleton("newFilter");
        modified = VariantContext.modifyFilters(cfg.vc, newFilters);
        Assert.assertEquals(modified.getFilters(), newFilters);

        modified = VariantContext.modifyAttribute(cfg.vc, "AC", 1);
        Assert.assertEquals(modified.getAttribute("AC"), 1);
        modified = VariantContext.modifyAttribute(modified, "AC", 2);
        Assert.assertEquals(modified.getAttribute("AC"), 2);
        modified = VariantContext.modifyAttributes(modified, null);
        Assert.assertTrue(modified.getAttributes().isEmpty());

        Genotype g1 = new Genotype("AA2", Arrays.asList(Aref, Aref), 10);
        Genotype g2 = new Genotype("AT2", Arrays.asList(Aref, T), 10);
        Genotype g3 = new Genotype("TT2", Arrays.asList(T, T), 10);
        GenotypesContext gc = GenotypesContext.create(g1,g2,g3);
        modified = VariantContext.modifyGenotypes(cfg.vc, gc);
        Assert.assertEquals(modified.getGenotypes(), gc);
        modified = VariantContext.modifyGenotypes(cfg.vc, null);
        Assert.assertTrue(modified.getGenotypes().isEmpty());

        // test that original hasn't changed
        Assert.assertEquals(cfg.vc.getChr(), cfg.copy.getChr());
        Assert.assertEquals(cfg.vc.getStart(), cfg.copy.getStart());
        Assert.assertEquals(cfg.vc.getEnd(), cfg.copy.getEnd());
        Assert.assertEquals(cfg.vc.getAlleles(), cfg.copy.getAlleles());
        Assert.assertEquals(cfg.vc.getAttributes(), cfg.copy.getAttributes());
        Assert.assertEquals(cfg.vc.getID(), cfg.copy.getID());
        Assert.assertEquals(cfg.vc.getGenotypes(), cfg.copy.getGenotypes());
        Assert.assertEquals(cfg.vc.getNegLog10PError(), cfg.copy.getNegLog10PError());
        Assert.assertEquals(cfg.vc.getFilters(), cfg.copy.getFilters());
    }

    // --------------------------------------------------------------------------------
    //
    // Test subcontext
    //
    // --------------------------------------------------------------------------------
    private class SubContextTest extends TestDataProvider {
        Set<String> samples;
        boolean updateAlleles;

        private SubContextTest(Collection<String> samples, boolean updateAlleles) {
            super(SubContextTest.class);
            this.samples = new HashSet<String>(samples);
            this.updateAlleles = updateAlleles;
        }

        public String toString() {
            return String.format("%s samples=%s updateAlleles=%b", super.toString(), samples, updateAlleles);
        }
    }

    @DataProvider(name = "SubContextTest")
    public Object[][] MakeSubContextTest() {
        for ( boolean updateAlleles : Arrays.asList(true, false)) {
            new SubContextTest(Collections.<String>emptySet(), updateAlleles);
            new SubContextTest(Collections.singleton("AA"), updateAlleles);
            new SubContextTest(Collections.singleton("AT"), updateAlleles);
            new SubContextTest(Collections.singleton("TT"), updateAlleles);
            new SubContextTest(Arrays.asList("AA", "AT"), updateAlleles);
            new SubContextTest(Arrays.asList("AA", "AT", "TT"), updateAlleles);
        }

        return SubContextTest.getTests(SubContextTest.class);
    }

    private final static void SubContextTest() {
    }

    @Test(dataProvider = "SubContextTest")
    public void runSubContextTest(SubContextTest cfg) {
        Genotype g1 = new Genotype("AA", Arrays.asList(Aref, Aref), 10);
        Genotype g2 = new Genotype("AT", Arrays.asList(Aref, T), 10);
        Genotype g3 = new Genotype("TT", Arrays.asList(T, T), 10);

        GenotypesContext gc = GenotypesContext.create(g1, g2, g3);
        VariantContext vc = new VariantContext("genotypes", VCFConstants.EMPTY_ID_FIELD, snpLoc, snpLocStart, snpLocStop, Arrays.asList(Aref, T), gc);
        VariantContext sub = cfg.updateAlleles ? vc.subContextFromSamples(cfg.samples) : vc.subContextFromSamples(cfg.samples, vc.getAlleles());

        // unchanged attributes should be the same
        Assert.assertEquals(sub.getChr(), vc.getChr());
        Assert.assertEquals(sub.getStart(), vc.getStart());
        Assert.assertEquals(sub.getEnd(), vc.getEnd());
        Assert.assertEquals(sub.getNegLog10PError(), vc.getNegLog10PError());
        Assert.assertEquals(sub.getFilters(), vc.getFilters());
        Assert.assertEquals(sub.getID(), vc.getID());
        Assert.assertEquals(sub.getReferenceBaseForIndel(), vc.getReferenceBaseForIndel());
        Assert.assertEquals(sub.getAttributes(), vc.getAttributes());

        Set<Genotype> expectedGenotypes = new HashSet<Genotype>();
        if ( cfg.samples.contains(g1.getSampleName()) ) expectedGenotypes.add(g1);
        if ( cfg.samples.contains(g2.getSampleName()) ) expectedGenotypes.add(g2);
        if ( cfg.samples.contains(g3.getSampleName()) ) expectedGenotypes.add(g3);
        GenotypesContext expectedGC = GenotypesContext.copy(expectedGenotypes);

        // these values depend on the results of sub
        if ( cfg.updateAlleles ) {
            // do the work to see what alleles should be here, and which not
            Set<Allele> alleles = new HashSet<Allele>();
            for ( final Genotype g : expectedGC ) alleles.addAll(g.getAlleles());
            if ( ! alleles.contains(Aref) ) alleles.add(Aref); // always have the reference
            Assert.assertEquals(new HashSet<Allele>(sub.getAlleles()), alleles);
        } else {
            // not updating alleles -- should be the same
            Assert.assertEquals(sub.getAlleles(), vc.getAlleles());
        }

        // same sample names => success
        Assert.assertEquals(sub.getGenotypes().getSampleNames(), expectedGC.getSampleNames());
    }
}