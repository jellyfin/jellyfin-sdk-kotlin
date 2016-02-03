package mediabrowser.model.dto;

import mediabrowser.model.entities.*;
import mediabrowser.model.globalization.*;
import mediabrowser.model.providers.*;

public class MetadataEditorInfo
{
	private java.util.ArrayList<ParentalRating> ParentalRatingOptions;
	public final java.util.ArrayList<ParentalRating> getParentalRatingOptions()
	{
		return ParentalRatingOptions;
	}
	public final void setParentalRatingOptions(java.util.ArrayList<ParentalRating> value)
	{
		ParentalRatingOptions = value;
	}
	private java.util.ArrayList<CountryInfo> Countries;
	public final java.util.ArrayList<CountryInfo> getCountries()
	{
		return Countries;
	}
	public final void setCountries(java.util.ArrayList<CountryInfo> value)
	{
		Countries = value;
	}
	private java.util.ArrayList<CultureDto> Cultures;
	public final java.util.ArrayList<CultureDto> getCultures()
	{
		return Cultures;
	}
	public final void setCultures(java.util.ArrayList<CultureDto> value)
	{
		Cultures = value;
	}
	private java.util.ArrayList<ExternalIdInfo> ExternalIdInfos;
	public final java.util.ArrayList<ExternalIdInfo> getExternalIdInfos()
	{
		return ExternalIdInfos;
	}
	public final void setExternalIdInfos(java.util.ArrayList<ExternalIdInfo> value)
	{
		ExternalIdInfos = value;
	}

	private String ContentType;
	public final String getContentType()
	{
		return ContentType;
	}
	public final void setContentType(String value)
	{
		ContentType = value;
	}
	private java.util.ArrayList<NameValuePair> ContentTypeOptions;
	public final java.util.ArrayList<NameValuePair> getContentTypeOptions()
	{
		return ContentTypeOptions;
	}
	public final void setContentTypeOptions(java.util.ArrayList<NameValuePair> value)
	{
		ContentTypeOptions = value;
	}

	public MetadataEditorInfo()
	{
		setParentalRatingOptions(new java.util.ArrayList<ParentalRating>());
		setCountries(new java.util.ArrayList<CountryInfo>());
		setCultures(new java.util.ArrayList<CultureDto>());
		setExternalIdInfos(new java.util.ArrayList<ExternalIdInfo>());
		setContentTypeOptions(new java.util.ArrayList<NameValuePair>());
	}
}