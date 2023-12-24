package my_click.enums;

public enum StatusEnum {
    SUCCESS(1),
    FAIL(2),
    IN_PROCESS(3);
    public final int index;

    public int getIndex() {
        return index;
    }

    StatusEnum(int index) {
        this.index = index;
    }

    public static StatusEnum getByIndex(int index) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.getIndex() == index) {
                return statusEnum;
            }
        }
        return IN_PROCESS;
    }
}
