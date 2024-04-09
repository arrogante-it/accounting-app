package com.arroganteIT.app.persistence.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserNameConverter implements AttributeConverter<UserNameFields, String> {

    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(UserNameFields userName) {
        if (userName == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        if (userName.getLastName() != null && !userName.getLastName().isEmpty()) {
            stringBuilder.append(userName.getLastName());
            stringBuilder.append(SEPARATOR);
        }

        if (userName.getName() != null && !userName.getName().isEmpty()) {
            stringBuilder.append(userName.getName());
        }
        return stringBuilder.toString();
    }

    @Override
    public UserNameFields convertToEntityAttribute(String dbUserName) {
        if (dbUserName == null || dbUserName.isEmpty()) {
            return null;
        }

        String[] pieces = dbUserName.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        UserNameFields userName = new UserNameFields();

        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbUserName.contains(SEPARATOR)) {
            userName.setLastName(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null && !pieces[1].isEmpty()) {
                userName.setName(pieces[1]);
            }
        } else {
            userName.setName(firstPiece);
        }

        return userName;
    }
}
