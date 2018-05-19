/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cloud
 */
@XmlRootElement
public class PositionInputBody {
    @XmlElement public Double lat;
    @XmlElement public Double lng;
    @XmlElement public String start;
    @XmlElement public String end;
}
