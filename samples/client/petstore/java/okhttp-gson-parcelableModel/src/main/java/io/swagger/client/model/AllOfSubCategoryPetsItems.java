/*
 * Swagger Petstore
 * This spec is mainly for testing Petstore server and contains fake endpoints, models. Please do not use this for any other purpose. Special characters: \" \\
 *
 * OpenAPI spec version: 1.0.0
 * Contact: apiteam@swagger.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.client.model.Category;
import io.swagger.client.model.Pet;
import io.swagger.client.model.Tag;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;
/**
 * AllOfSubCategoryPetsItems
 */


public class AllOfSubCategoryPetsItems extends Pet implements Parcelable {
  public AllOfSubCategoryPetsItems() {
    super();
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AllOfSubCategoryPetsItems {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public void writeToParcel(Parcel out, int flags) {
    super.writeToParcel(out, flags);
  }

  AllOfSubCategoryPetsItems(Parcel in) {
    super(in);
  }

  public int describeContents() {
    return 0;
  }

  public static final Parcelable.Creator<AllOfSubCategoryPetsItems> CREATOR = new Parcelable.Creator<AllOfSubCategoryPetsItems>() {
    public AllOfSubCategoryPetsItems createFromParcel(Parcel in) {
      return new AllOfSubCategoryPetsItems(in);
    }
    public AllOfSubCategoryPetsItems[] newArray(int size) {
      return new AllOfSubCategoryPetsItems[size];
    }
  };
}
