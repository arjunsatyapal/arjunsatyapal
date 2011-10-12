package com.smartgwt.sample.server;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.datanucleus.jpa.annotations.Extension;

@Entity
public class City
    implements Serializable
{

    @Id
    @Column (nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Extension (vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String cityId;

    @Column (nullable = false)
    private String cityName;

    @Column (nullable = false)
    @Extension (vendorName = "datanucleus", key = "gae.parent-pk", value = "true")
    private String countryId;

    @ManyToOne (fetch=FetchType.LAZY)
    private Country country;

    public City ()
    {
    }

    public String getCityId ()
    {
        return cityId;
    }

    public void setCityId (String cityId)
    {
        this.cityId = cityId;
    }

    public String getCityName ()
    {
        return cityName;
    }

    public void setCityName (String cityName)
    {
        this.cityName = cityName;
    }

    public String getCountryId ()
    {
        return countryId;
    }

    public void setCountryId (String countryId)
    {
        this.countryId = countryId;
    }

    public Country getCountry () {
        return country;
    }

    public void setCountry (Country country) {
        this.country = country;
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
               + "cityId=" + ((getCityId() == null) ? "null" : getCityId().toString())
               + ", "
               + "cityName=" + ((getCityName() == null) ? "null" : getCityName().toString())
               + "]";
    }

}
