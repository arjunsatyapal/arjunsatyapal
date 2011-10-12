package com.smartgwt.sample.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.datanucleus.jpa.annotations.Extension;

@Entity
public class Country
    implements Serializable
{

    @Id
    @Column (nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Extension (vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String countryId;

    @Column (nullable = false)
    private String countryCode;

    @Column (nullable = false)
    private String countryName;

    @OneToMany (cascade=CascadeType.ALL)
    private List<City> cities;

    public Country ()
    {
        cities = new ArrayList<City>();
    }

    public String getCountryId ()
    {
        return countryId;
    }

    public void setCountryId (String countryId)
    {
        this.countryId = countryId;
    }

    public String getCountryCode ()
    {
        return countryCode;
    }

    public void setCountryCode (String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getCountryName ()
    {
        return countryName;
    }

    public void setCountryName (String countryName)
    {
        this.countryName = countryName;
    }

    public List<City> getCities ()
    {
        return cities;
    }

    public void setCities (List<City> cities)
    {
        this.cities = cities;
    }

    /**
     * Returns a string representation of the object. Resulting string contains
     * full name of the class and list of its properties and their values.
     *
     * @return <code>String</code> representation of this object.
     */
    @Override
    public String toString ()
    {
        return getClass().getName()
               + "["
               + "countryId=" + ((getCountryId() == null) ? "null" : getCountryId().toString())
               + ", "
               + "countryCode=" + ((getCountryCode() == null) ? "null" : getCountryCode().toString())
               + ", "
               + "countryName=" + ((getCountryName() == null) ? "null" : getCountryName().toString())
               + "]";
    }

}
