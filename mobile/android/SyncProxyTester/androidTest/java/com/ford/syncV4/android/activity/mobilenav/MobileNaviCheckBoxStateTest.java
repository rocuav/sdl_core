package com.ford.syncV4.android.activity.mobilenav;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.CheckBox;

import com.ford.syncV4.android.R;
import com.ford.syncV4.android.activity.SyncProxyTester;

/**
 * Created by Andrew Batutin on 8/30/13.
 */
public class MobileNaviCheckBoxStateTest extends ActivityInstrumentationTestCase2<SyncProxyTester> {

    private MobileNaviCheckBoxState sut;

    public MobileNaviCheckBoxStateTest() {
        super(SyncProxyTester.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        CheckBox box = new CheckBox(getActivity());
        sut = new MobileNaviCheckBoxState(box, getActivity());
    }

    public void testInitialStateOff() throws Exception {
        assertEquals("should have empty text", "", sut.getItem().getText());
        assertEquals("should have video checkbox hint",  sut.getItem().getHint(), getActivity().getString(R.string.service_check_view_on));
        assertTrue("should be enabled", sut.getItem().isEnabled());
        assertEquals("should be at OFF state", sut.getState(), CheckBoxStateValue.OFF);
    }

    public void testStateDisabled() throws Exception {
        sut.setStateDisabled();
        assertEquals("check box should be disabled", false, sut.getItem().isEnabled());
    }

    public void testStateOff() throws Exception {
        sut.setStateOff();
        assertEquals("should have empty text", "", sut.getItem().getText());
        assertEquals("should have video checkbox hint",  sut.getItem().getHint(), getActivity().getString(R.string.service_check_view_on));
        assertTrue("should be enabled", sut.getItem().isEnabled());
        assertEquals("should be at OFF state", sut.getState(), CheckBoxStateValue.OFF);
    }

    public void testStateOn() throws Exception {
        sut.setStateOn();
        assertEquals("should have video streaming text", getActivity().getString(R.string.service_check_view_off), sut.getItem().getText());
        assertTrue("should be enabled", sut.getItem().isEnabled());
        assertEquals("should be at ON state", sut.getState(), CheckBoxStateValue.ON);
    }

    public void testTransitionFormOffToDisabled() throws Exception {
        sut.setStateOff();
        sut.setStateDisabled();
        assertEquals("check box should be disabled", false, sut.getItem().isEnabled());
        assertEquals("should be at DISABLED state", sut.getState(), CheckBoxStateValue.DISABLED);
    }

    public void testTransitionFromDisabledToOn() throws Exception {
        sut.setStateOff();
        sut.setStateDisabled();
        sut.setStateOn();
        assertEquals("should have video streaming text", getActivity().getString(R.string.service_check_view_off), sut.getItem().getText());
        assertTrue("should be enabled", sut.getItem().isEnabled());
        assertEquals("should be at ON state", sut.getState(), CheckBoxStateValue.ON);
    }

    public void testTransitionFromOnToDisabled() throws Exception {
        sut.setStateOff();
        sut.setStateDisabled();
        sut.setStateOn();
        sut.setStateDisabled();
        assertEquals("check box should be disabled", false, sut.getItem().isEnabled());
        assertEquals("should have video streaming text", getActivity().getString(R.string.service_check_view_off), sut.getItem().getText());
        assertEquals("should be at DISABLED state", sut.getState(), CheckBoxStateValue.DISABLED);
    }

    public void testTransitionFromDisabledToOff() throws Exception {
        sut.setStateOff();
        sut.setStateDisabled();
        sut.setStateOn();
        sut.setStateDisabled();
        sut.setStateOff();
        assertEquals("should have empty text", "", sut.getItem().getText());
        assertEquals("should have video checkbox hint",  sut.getItem().getHint(), getActivity().getString(R.string.service_check_view_on));
        assertTrue("should be enabled", sut.getItem().isEnabled());
        assertEquals("should be at OFF state", sut.getState(), CheckBoxStateValue.OFF);
    }
}