package com.unibg.UnibgProject.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CheckinList implements Serializable {

    private List<Checkin> checkinList;
}
