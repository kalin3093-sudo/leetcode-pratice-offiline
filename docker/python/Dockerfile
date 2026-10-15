# Cojudge Python Runner Image
#
# WHY: python:3.11-slim lacks /usr/bin/time (runtime/memory metrics) and offline
# machines cannot apt-get install it at run time.

FROM python:3.11-slim

RUN apt-get update -qq && apt-get install -y -qq time && \
    rm -rf /var/lib/apt/lists/*
