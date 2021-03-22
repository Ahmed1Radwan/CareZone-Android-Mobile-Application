
package com.ahmedhamdy.healthcare;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.DataQueryBuilder;

import java.util.Date;
import java.util.List;

public class MedicalVirusInfo
{
  private String virusImageUrl;
  private String virusName;
  private String objectId;
  private String ownerId;
  private String medicineImageUrl;
  private Date created;
  private String medicineDescription;
  private Date updated;
  private String medicineWebsiteUrl;
  private String medicineName;
  public String getVirusImageUrl()
  {
    return virusImageUrl;
  }

  public void setVirusImageUrl( String virusImageUrl )
  {
    this.virusImageUrl = virusImageUrl;
  }

  public String getVirusName()
  {
    return virusName;
  }

  public void setVirusName( String virusName )
  {
    this.virusName = virusName;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getMedicineImageUrl()
  {
    return medicineImageUrl;
  }

  public void setMedicineImageUrl( String medicineImageUrl )
  {
    this.medicineImageUrl = medicineImageUrl;
  }

  public Date getCreated()
  {
    return created;
  }

  public String getMedicineDescription()
  {
    return medicineDescription;
  }

  public void setMedicineDescription( String medicineDescription )
  {
    this.medicineDescription = medicineDescription;
  }

  public Date getUpdated()
  {
    return updated;
  }

  public String getMedicineWebsiteUrl()
  {
    return medicineWebsiteUrl;
  }

  public void setMedicineWebsiteUrl( String medicineWebsiteUrl )
  {
    this.medicineWebsiteUrl = medicineWebsiteUrl;
  }

  public String getMedicineName()
  {
    return medicineName;
  }

  public void setMedicineName( String medicineName )
  {
    this.medicineName = medicineName;
  }

                                                    
  public MedicalVirusInfo save()
  {
    return Backendless.Data.of( MedicalVirusInfo.class ).save( this );
  }

  public void saveAsync( AsyncCallback<MedicalVirusInfo> callback )
  {
    Backendless.Data.of( MedicalVirusInfo.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( MedicalVirusInfo.class ).remove( this );
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( MedicalVirusInfo.class ).remove( this, callback );
  }

  public static MedicalVirusInfo findById(String id )
  {
    return Backendless.Data.of( MedicalVirusInfo.class ).findById( id );
  }

  public static void findByIdAsync( String id, AsyncCallback<MedicalVirusInfo> callback )
  {
    Backendless.Data.of( MedicalVirusInfo.class ).findById( id, callback );
  }

  public static MedicalVirusInfo findFirst()
  {
    return Backendless.Data.of( MedicalVirusInfo.class ).findFirst();
  }

  public static void findFirstAsync( AsyncCallback<MedicalVirusInfo> callback )
  {
    Backendless.Data.of( MedicalVirusInfo.class ).findFirst( callback );
  }

  public static MedicalVirusInfo findLast()
  {
    return Backendless.Data.of( MedicalVirusInfo.class ).findLast();
  }

  public static void findLastAsync( AsyncCallback<MedicalVirusInfo> callback )
  {
    Backendless.Data.of( MedicalVirusInfo.class ).findLast( callback );
  }

  public static List<MedicalVirusInfo> find(DataQueryBuilder queryBuilder )
  {
    return Backendless.Data.of( MedicalVirusInfo.class ).find( queryBuilder );
  }

  public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<MedicalVirusInfo>> callback )
  {
    Backendless.Data.of( MedicalVirusInfo.class ).find( queryBuilder, callback );
  }

  @Override
  public String toString() {
    return "MedicalVirusInfo{" +
            "virusImageUrl='" + virusImageUrl + '\'' +
            ", virusName='" + virusName + '\'' +
            ", medicineImageUrl='" + medicineImageUrl + '\'' +
            ", medicineDescription='" + medicineDescription + '\'' +
            ", medicineWebsiteUrl='" + medicineWebsiteUrl + '\'' +
            ", medicineName='" + medicineName + '\'' +
            '}';
  }
}