package com.pb.models.ctrampIf;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.pb.common.calculator.IndexValues;
import com.pb.common.calculator.VariableTable;

/**
 * @author crf <br/>
 *         Started: Nov 14, 2008 3:32:58 PM
 */
public class StopDCSoaDMU implements SoaDMU, Serializable, VariableTable {

    protected transient Logger logger = Logger.getLogger(StopDCSoaDMU.class);

    protected HashMap<String, Integer> methodIndexMap;
    
    private HouseholdIf hh;
    protected TourIf tour;
    
    private int inboundStop;
    private int kidsPresent;
    protected IndexValues dmuIndex = null;
    private double[] logSizeTerms;

    protected int[] altToZone;
    protected int[] altToSubZone;

    private TazDataIf tazDataManager;
    private ModelStructure modelStructure;

    public StopDCSoaDMU(TazDataIf tazDataManager, ModelStructure modelStructure){
        this.modelStructure = modelStructure;
        this.tazDataManager = tazDataManager;
        altToZone = tazDataManager.getAltToZoneArray();
        altToSubZone = tazDataManager.getAltToSubZoneArray();
        dmuIndex = new IndexValues();
    }

    public void setDmuState(int zoneId, int stopOriginTaz, int tourDest, HouseholdIf hh, TourIf tour, boolean inboundStop, boolean kidsPresent, double[] logSizeTerms) {
        this.hh = hh;
        this.tour = tour;
        //set index values
        dmuIndex.setHHIndex(hh.getHhId());
        dmuIndex.setZoneIndex(zoneId);
        dmuIndex.setOriginZone(stopOriginTaz);
        dmuIndex.setDestZone( tourDest );
        dmuIndex.setDebug( hh.getDebugChoiceModels() );
        this.inboundStop = inboundStop ? 1 : 0;
        this.kidsPresent = kidsPresent ? 1 : 0;
        this.logSizeTerms = logSizeTerms;
    }

    public HouseholdIf getHouseholdObject() {
        return hh;
    }

    public IndexValues getDmuIndexValues() {
        return dmuIndex;
    }

    public int getInbound() {
        return inboundStop;
    }

    public int getTourIsJoint() {
        return tour.getTourCategoryIsJointNonMandatory() ? 1 : 0;
    }    
    
    public int getKidsPresent() {
        return kidsPresent;
    }
    
    public int getTourOriginZone() {
        return tour.getTourOrigTaz();
    }

    public int getTourDestZone() {
        return tour.getTourDestTaz();
    }
    
    public int getTourModeIsWalk() {
        boolean tourModeIsWalk = modelStructure.getTourModeIsWalk( tour.getTourModeChoice() );
        return tourModeIsWalk ? 1 : 0;
    }

    public int getTourModeIsBike() {
        boolean tourModeIsBike = modelStructure.getTourModeIsBike( tour.getTourModeChoice() );
        return tourModeIsBike ? 1 : 0;
    }


    public int getTourModeIsWalkLocal() {
        boolean tourModeIsWalkLocal = modelStructure.getTourModeIsWalkLocal( tour.getTourModeChoice() );
        return tourModeIsWalkLocal ? 1 : 0;
    }

    public int getTourModeIsWalkPremium() {
        boolean tourModeIsWalkPremium = modelStructure.getTourModeIsWalkPremium( tour.getTourModeChoice() );
        return tourModeIsWalkPremium ? 1 : 0;
    }

    public int getTourModeIsWalkTransit() {
        boolean tourModeIsWalkLocal = modelStructure.getTourModeIsWalkLocal( tour.getTourModeChoice() );
        boolean tourModeIsWalkPremium = modelStructure.getTourModeIsWalkPremium( tour.getTourModeChoice() );
        return tourModeIsWalkPremium || tourModeIsWalkLocal ? 1 : 0;
    }
    
    public int getTourModeIsDriveTransit() {
        boolean tourModeIsDriveTransit = modelStructure.getTourModeIsDriveTransit( tour.getTourModeChoice() );
        return tourModeIsDriveTransit ? 1 : 0;
    }


    public double getLnStopDcSizeAlt(int alt) {
        return logSizeTerms[alt];
    }

    public int getStopDestAreaTypeAlt(int alt) {
        int[] at = tazDataManager.getZonalAreaType();
        return at[altToZone[alt]-1];
    }
    
    public int getIndexValue(String variableName) {
        return methodIndexMap.get(variableName);
    }



    public int getAssignmentIndexValue(String variableName) {
        throw new UnsupportedOperationException();
    }

    public double getValueForIndex(int variableIndex) {
        throw new UnsupportedOperationException();
    }

    public double getValueForIndex(int variableIndex, int arrayIndex) {
        throw new UnsupportedOperationException();
    }

    public void setValue(String variableName, double variableValue) {
        throw new UnsupportedOperationException();
    }

    public void setValue(int variableIndex, double variableValue) {
        throw new UnsupportedOperationException();
    }
    
}
