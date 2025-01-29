package com.example.watch_list.exceptions;

import java.util.List;

public record ApiErrorWrapper(List<String> errors) {
}
