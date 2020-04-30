package org.jellyfin.apiclient.model.dto;

/**
 * In the C# land we call this a NameGuidPair for some reason
 */
public class GenreDto {
    /**
     * Gets or sets the name.
     *
     * <value>The name.</value>
     */
    private String Name;

    public final String getName() {
        return Name;
    }

    public final void setName(String value) {
        Name = value;
    }

    /**
     * Gets or sets the identifier.
     *
     * <value>The identifier.</value>
     */
    private String Id;

    public final String getId() {
        return Id;
    }

    public final void setId(String value) {
        Id = value;
    }
}